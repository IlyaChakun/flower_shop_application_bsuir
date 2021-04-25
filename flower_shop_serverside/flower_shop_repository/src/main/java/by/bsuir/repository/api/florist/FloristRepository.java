package by.bsuir.repository.api.florist;

import by.bsuir.entity.florist.Florist;
import by.bsuir.repository.api.core.AbstractRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FloristRepository extends AbstractRepository<Florist> {

    Optional<Florist> findByActiveOrdersCountBetweenAndFloristStatisticFloristRatingBetween(Integer orderCountStart,
            Integer ordersCountFinish, Double ratingStart, Double ratingFinish);

    Florist getByUserEmail(String email);

    Florist getOneByUserId(Long userId);

    @Query("select id from Florist f where f.user.id=?1")
    Long getFloristIdByUserId(Long user);

    List<Florist> findAllByDateOfCreationIsBetween(LocalDateTime startDate, LocalDateTime endDate);
}
