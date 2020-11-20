package by.bsuir.repository.api;

import by.bsuir.entity.product.bouqet.BouquetType;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface BouquetTypeRepository extends AbstractRepository<BouquetType> {

    Page<BouquetType> findAll(Pageable pageable);
}
