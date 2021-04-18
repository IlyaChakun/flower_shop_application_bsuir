package by.bsuir.repository.api.product;

import by.bsuir.entity.product.Product;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends AbstractRepository<Product> {

    Page<Product> findAllByIsDeletedFalse(Specification specification, Pageable pageable);

    Page<Product> findAllByIsDeletedTrue(Pageable pageable);
}
