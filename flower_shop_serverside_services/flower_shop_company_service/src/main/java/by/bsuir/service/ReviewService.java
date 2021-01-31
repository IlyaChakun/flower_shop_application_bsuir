package by.bsuir.service;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.review.ReviewDTO;
import by.bsuir.service.core.base.FindAllOperationService;
import by.bsuir.service.core.base.SaveOperationService;

public interface ReviewService extends SaveOperationService<ReviewDTO>, FindAllOperationService<ReviewDTO> {

    PageWrapper<ReviewDTO> findAll(int page, int size);

}
