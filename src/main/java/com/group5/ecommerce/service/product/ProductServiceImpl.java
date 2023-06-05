package com.group5.ecommerce.service.product;

import com.group5.ecommerce.dto.product.CreateProductDto;
import com.group5.ecommerce.dto.product.UpdateProductDto;
import com.group5.ecommerce.entity.*;
import com.group5.ecommerce.entity.enums.SearchOrder;
import com.group5.ecommerce.entity.enums.SortBy;
import com.group5.ecommerce.exception.NotFoundException;
import com.group5.ecommerce.repository.*;
import com.group5.ecommerce.repository.product.ProductRepository;
import com.group5.ecommerce.response.MessageResponse;
import com.group5.ecommerce.response.PaginatedResponse;
import com.group5.ecommerce.response.product.DetailProductResponse;
import com.group5.ecommerce.response.product.ProductMapper;
import com.group5.ecommerce.response.product.ProductResponse;
import com.group5.ecommerce.utils.CloudinaryUtils;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CloudinaryUtils cloudinaryUtils;

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final ColorRepository colorRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    @Override
    public PaginatedResponse<ProductResponse> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Product> paginatedProducts = this.productRepository.findAll(pageable);

        List<Product> products = paginatedProducts.getContent();

        List<ProductResponse> mappedProducts = ProductMapper.INSTANCE.toListResponse(products);

        return new PaginatedResponse<>(
                mappedProducts,
                paginatedProducts.getNumber(),
                paginatedProducts.getSize(),
                paginatedProducts.getTotalElements(),
                paginatedProducts.getTotalPages(),
                paginatedProducts.isLast()
        );
    }

    @Override
    public PaginatedResponse<ProductResponse> searchProducts(
            String query,
            SortBy sortBy,
            SearchOrder order,
            String categoryName,
            String brandName,
            List<String> colorNames,
            BigDecimal limitPrice,
            boolean inOffer,
            int page,
            int size
    ) {
        Category category = null;
        if (categoryName != null)
            category = this.categoryRepository
                    .findByName(categoryName)
                    .orElseThrow(
                            () -> new NotFoundException("La categoría de nombre " + categoryName + " no existe")
                    );

        Brand brand = null;
        if (brandName != null)
            brand = this.brandRepository
                    .findByName(brandName)
                    .orElseThrow(
                            () -> new NotFoundException("La marca de nombre " + brandName + " no existe")
                    );

        if (colorNames != null)
            for (String colorName : colorNames) {
                this.colorRepository
                        .findByName(colorName)
                        .orElseThrow(
                                () -> new NotFoundException("El color de nombre " + colorName + " no existe")
                        );
            }

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> paginatedProducts = this.productRepository.searchBy(
                query,
                sortBy,
                order,
                category,
                brand,
                colorNames,
                limitPrice,
                inOffer,
                pageable
        );

        List<ProductResponse> mappedProducts = ProductMapper.INSTANCE.toListResponse(paginatedProducts.getContent());

        return new PaginatedResponse<>(
                mappedProducts,
                paginatedProducts.getNumber(),
                paginatedProducts.getSize(),
                paginatedProducts.getTotalElements(),
                paginatedProducts.getTotalPages(),
                paginatedProducts.isLast()
        );
    }

    @Override
    public DetailProductResponse detailProduct(Long productId) {
        Optional<Product> product = this.productRepository.findById(productId);

        if (product.isEmpty()) throw new NotFoundException("El producto de ID " + productId + " no existe");

        return ProductMapper.INSTANCE.toResponse(product.get());
    }

    @Override
    public DetailProductResponse saveProduct(CreateProductDto productData) {
        var product = new Product();

        product.setTitle(productData.getTitle());
        product.setDescription(productData.getDescription());
        product.setOriginalPrice(productData.getOriginalPrice());
        product.setDiscountRate(productData.getDiscountRate());
        product.setPointValue(productData.getPointValue());
        product.setQuantity(productData.getQuantity());

        List<Color> colors = new ArrayList<>();

        for (Long colorId : productData.getColors()) {
            var color = this.colorRepository
                    .findById(colorId)
                    .orElseThrow(
                            () -> new NotFoundException(
                                    "El color de ID " + colorId + " no existe"
                            )
                    );
            colors.add(color);
        }

        product.setColors(colors);

        Category category = this.categoryRepository
                .findById(productData.getCategory())
                .orElseThrow(
                        () -> new NotFoundException(
                                "La categoría de ID" + productData.getCategory() + " no existe"
                        )
                );

        product.setCategory(category);

        Brand brand = this.brandRepository
                .findById(productData.getBrand())
                .orElseThrow(
                        () -> new NotFoundException(
                                "La marca de ID " + productData.getBrand() + " no existe"
                        )
                );

        product.setBrand(brand);

        var savedProduct = this.productRepository.save(product);

        List<Image> images = new ArrayList<>();

        try {
            for (MultipartFile fileImage : productData.getImages()) {
                String imageUrl = cloudinaryUtils.uploadImage(fileImage);

                var image = Image.builder()
                        .imageUrl(imageUrl)
                        .product(savedProduct)
                        .build();
                this.imageRepository.save(image);

                images.add(image);
            }
        } catch (IOException exception) {
            throw new RuntimeException("La imagen no se ha podido subir");
        }

        savedProduct.setImages(images);

        return ProductMapper.INSTANCE.toResponse(savedProduct);
    }

    @Override
    public DetailProductResponse updateProduct(Long productId, UpdateProductDto productData) {
        Product product = this.productRepository
                .findById(productId)
                .orElseThrow(
                        () -> new NotFoundException("El producto no existe")
                );

        if (productData.getTitle() != null)
            product.setTitle(productData.getTitle());

        if (productData.getDescription() != null)
            product.setDescription(productData.getDescription());

        if (productData.getOriginalPrice() != null)
            product.setOriginalPrice(productData.getOriginalPrice());

        if (productData.getDiscountRate() != null)
            product.setDiscountRate(productData.getDiscountRate());

        if (productData.getPointValue() != null)
            product.setPointValue(productData.getPointValue());

        if (productData.getQuantity() != null)
            product.setQuantity(productData.getQuantity());

        if (productData.getCategory() != null) {
            var category = this.categoryRepository
                    .findById(productData.getCategory())
                    .orElseThrow(
                            () -> new NotFoundException("La categoría no existe")
                    );
            product.setCategory(category);
        }

        if (productData.getBrand() != null) {
            var brand = this.brandRepository
                    .findById(productData.getBrand())
                    .orElseThrow(
                            () -> new NotFoundException("La marca no existe")
                    );
            product.setBrand(brand);
        }

        Product updatedProduct = this.productRepository.save(product);

        return ProductMapper.INSTANCE.toResponse(updatedProduct);
    }

    @Override
    public MessageResponse deleteProduct(Long productId) {
        var product = this.productRepository
                .findById(productId)
                .orElseThrow(
                        () -> new NotFoundException("El producto no existe")
                );

        this.productRepository.delete(product);

        return new MessageResponse("El producto de ID " + productId + " ha sido eliminado");
    }

}
