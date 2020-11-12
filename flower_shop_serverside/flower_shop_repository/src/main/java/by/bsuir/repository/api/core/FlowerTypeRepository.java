package by.bsuir.repository.api.core;

import by.bsuir.entity.product.flower.FlowerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerTypeRepository extends AbstractRepository<FlowerType> {

    Page<FlowerType> findAll(Pageable pageable);
}
