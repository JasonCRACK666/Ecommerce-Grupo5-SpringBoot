package com.group5.ecommerce.repository.product;

import com.group5.ecommerce.entity.Brand;
import com.group5.ecommerce.entity.Category;
import com.group5.ecommerce.entity.Color;
import com.group5.ecommerce.entity.Product;
import com.group5.ecommerce.entity.enums.SearchOrder;
import com.group5.ecommerce.entity.enums.SortBy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CustomProductRepositoryImpl implements CustomProductRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Page<Product> searchBy(
            String query,
            SortBy sortBy,
            SearchOrder order,
            Category category,
            Brand brand,
            List<String> colors,
            BigDecimal limitPrice,
            boolean inOffer,
            Pageable pageable
    ) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        Root<Product> productRoot = cq.from(Product.class);
        ListJoin<Product, Color> joinColors = productRoot.joinList("colors");

        Order orderBy = order.name().equals("asc")
                ? cb.asc(productRoot.get(sortBy.name()))
                : cb.desc(productRoot.get(sortBy.name()));

        List<Predicate> predicates = new ArrayList<>();

        if (query != null)
            predicates.add(cb.like(productRoot.get("title"), query + "%"));

        if (limitPrice != null)
            predicates.add(cb.lessThanOrEqualTo(productRoot.get("originalPrice").as(BigDecimal.class), limitPrice));

        if (category != null)
            predicates.add(cb.equal(productRoot.get("category"), category));

        if (brand != null)
            predicates.add(cb.equal(productRoot.get("brand"), brand));

        if (colors != null)
            if (!colors.isEmpty())
                predicates.add(joinColors.get("name").in(colors));

        if (inOffer)
            predicates.add(cb.isNotNull(productRoot.get("discountRate")));

        cq.select(productRoot).where(predicates.toArray(new Predicate[]{})).orderBy(orderBy);

        List<Product> products = entityManager
                .createQuery(cq)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        var countProducts = products.size();

        return new PageImpl<>(products, pageable, countProducts);
    }
}
