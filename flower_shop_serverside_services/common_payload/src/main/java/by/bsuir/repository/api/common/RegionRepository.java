package by.bsuir.repository.api.common;


import by.bsuir.entity.common.Region;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepository extends AbstractRepository<Region> {

    Optional<Region> findByRegionName(String regionName);

    Region getByRegionName(String regionName);
}
