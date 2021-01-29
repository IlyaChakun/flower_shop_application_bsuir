package by.bsuir.repository.api.review;

import by.bsuir.entity.review.Review;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends AbstractRepository<Review> {

    Optional<Review> findByEmail(String email);

}