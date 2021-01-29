package by.bsuir.flowershop.repository.api.review;

import by.bsuir.flowershop.entity.review.Review;
import by.bsuir.flowershop.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends AbstractRepository<Review> {

    Optional<Review> findByEmail(String email);

}