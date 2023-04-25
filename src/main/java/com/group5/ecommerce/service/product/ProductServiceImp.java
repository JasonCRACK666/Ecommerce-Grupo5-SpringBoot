package com.group5.ecommerce.service.product;

import com.group5.ecommerce.dto.product.CreateProductDto;
import com.group5.ecommerce.entity.*;
import com.group5.ecommerce.exception.notFound.NotFoundReqException;
import com.group5.ecommerce.repository.*;
import com.group5.ecommerce.utils.CloudinaryUtils;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final CloudinaryUtils cloudinaryUtils;

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final ColorRepository colorRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    @Override
    public Product saveProduct(CreateProductDto productData) {
        List<Image> images = new ArrayList<>();
        List<Color> colors = new ArrayList<>();

        try {
            for (MultipartFile fileImage : productData.getImages()) {
                String imageUrl = cloudinaryUtils.uploadImage(fileImage);

                var image = Image.builder()
                        .imageUrl(imageUrl)
                        .build();
                this.imageRepository.save(image);
            }
        } catch (IOException exception) {
            throw new RuntimeException("La imagen no se ha podido subir");
        }

        for (Long colorId : productData.getColors()) {
            var color = this.colorRepository.findById(colorId);

            if (color.isEmpty())
                throw new NotFoundReqException("El color de ID" + colorId + " no existe");

            colors.add(color.get());
        }

        Optional<Category> category = this.categoryRepository.findById(productData.getCategory());

        if (category.isEmpty())
            throw new NotFoundReqException("La categoría de ID" + productData.getCategory() + " no existe");

        Optional<Brand> brand = this.brandRepository.findById(productData.getBrand());

        if (brand.isEmpty())
            throw new NotFoundReqException("La marca de ID" + productData.getBrand() + " no existe");

        var product = Product.builder()
                .title(productData.getTitle())
                .description(productData.getDescription())
                .originalPrice(productData.getOriginalPrice())
                .discountRate(productData.getDiscountRate())
                .pointValue(productData.getPointValue())
                .quantity(productData.getQuantity())
                .category(category.get())
                .brand(brand.get())
                .colors(colors)
                .images(images)
                .build();

        return this.productRepository.save(product);
    }
}
