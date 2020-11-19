package by.bsuir.dto.mapper.review;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.review.ReviewDTO;
import by.bsuir.entity.review.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapperDTO extends AbstractMapperDTO<Review, ReviewDTO> {

    @Autowired
    public ReviewMapperDTO() {
        super(Review.class, ReviewDTO.class);
    }
}