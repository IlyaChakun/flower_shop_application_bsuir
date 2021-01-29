package by.bsuir.flowershop.repository.api.common;


import by.bsuir.flowershop.entity.common.Region;
import by.bsuir.flowershop.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepository extends AbstractRepository<Region> {

    Optional<Region> findByRegionName(String regionName);

    Region getByRegionName(String regionName);
}
