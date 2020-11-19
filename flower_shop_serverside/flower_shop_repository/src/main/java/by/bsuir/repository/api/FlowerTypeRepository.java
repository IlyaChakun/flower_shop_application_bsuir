package by.bsuir.repository.api;

import by.bsuir.entity.product.flower.FlowerType;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerTypeRepository extends AbstractRepository<FlowerType> {

    Page<FlowerType> findAll(Pageable pageable);
}
