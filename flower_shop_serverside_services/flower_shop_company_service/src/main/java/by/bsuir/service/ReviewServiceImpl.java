package by.bsuir.service;

import by.bsuir.repository.api.review.ReviewRepository;
import by.bsuir.dto.mapper.review.ReviewMapperDTO;
import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.review.ReviewDTO;
import by.bsuir.entity.review.Review;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapperDTO reviewMapper;
    private final ReviewRepository reviewRepository;


    @Override
    @Transactional
    public ReviewDTO save(ReviewDTO reviewDTO) {
        Review review = reviewMapper.toEntity(reviewDTO);
        Review saved = reviewRepository.save(review);
        return reviewMapper.toDto(saved);
    }


    @Override
    public List<ReviewDTO> findAll() {
        List<Review> reviews = reviewRepository.findAll();

        return reviewMapper.toDtoList(reviews);
    }


    @Override
    public PageWrapper<ReviewDTO> findAll(int page, int size) {
        Pageable pageable = getPageable(page, size);
        Page<Review> reviews = reviewRepository.findAll(pageable);

        return
                new PageWrapper<>(
                        reviewMapper.toDtoList(reviews.toList()),
                        reviews.getTotalPages(),
                        reviews.getTotalElements());
    }

    private Pageable getPageable(int page, int size) {
        return PageRequest.of(page, size);
    }
}