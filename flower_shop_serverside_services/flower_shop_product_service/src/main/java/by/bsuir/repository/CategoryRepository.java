package by.bsuir.repository;

import by.bsuir.entity.Category;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends AbstractRepository<Category> {

    //    List<Category> findAllByChildrenIsNotNull();
    List<Category> findDistinctByChildrenIsNotNull();
}
