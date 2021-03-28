package by.bsuir.repository.api.product;

import by.bsuir.entity.product.Category;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends AbstractRepository<Category> {

    List<Category> findDistinctByChildrenIsNotNull();

}
