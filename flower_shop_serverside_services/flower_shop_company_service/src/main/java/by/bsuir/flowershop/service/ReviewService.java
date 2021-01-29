package by.bsuir.flowershop.service;

import by.bsuir.flowershop.dto.model.PageWrapper;
import by.bsuir.flowershop.dto.model.review.ReviewDTO;
import by.bsuir.flowershop.service.core.base.FindAllOperationService;
import by.bsuir.flowershop.service.core.base.SaveOperationService;

public interface ReviewService extends SaveOperationService<ReviewDTO>, FindAllOperationService<ReviewDTO> {

    PageWrapper<ReviewDTO> findAll(int page, int size);

}
