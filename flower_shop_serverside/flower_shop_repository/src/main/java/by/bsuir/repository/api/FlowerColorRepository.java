package by.bsuir.repository.api;

import by.bsuir.entity.product.common.FlowerColor;
import by.bsuir.entity.product.flower.Flower;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerColorRepository extends AbstractRepository<FlowerColor> {

}
