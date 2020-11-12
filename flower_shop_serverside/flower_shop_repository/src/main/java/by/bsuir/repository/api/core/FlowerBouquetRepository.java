package by.bsuir.repository.api.core;

import by.bsuir.entity.product.bouqet.FlowerBouquet;
import by.bsuir.entity.product.flower.Flower;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerBouquetRepository extends AbstractRepository<FlowerBouquet> {

    Page<FlowerBouquet> findAll(Specification<Flower> specification, Pageable pageable);
}
