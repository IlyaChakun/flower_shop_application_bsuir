package by.bsuir.repository.specification;

import by.bsuir.entity.product.Product;
import org.springframework.data.jpa.domain.Specification;

public final class ProductSpecification {

    private ProductSpecification() {

    }

    public static Specification<Product> findByProductTitleLike(String title) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder
                        .like(root.get("title"), '%' + title + '%');
    }

    public static Specification<Product> findCategoryId(Long categoryId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder
                        .equal(root.get("categoryId"), categoryId);
    }

    public static Specification<Product> findByIsDeleted() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder
                        .equal(root.get("isDeleted"), false);
    }


}
