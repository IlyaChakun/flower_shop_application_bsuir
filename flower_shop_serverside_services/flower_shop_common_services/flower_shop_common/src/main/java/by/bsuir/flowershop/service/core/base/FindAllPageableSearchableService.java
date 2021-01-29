package by.bsuir.flowershop.service.core.base;

import by.bsuir.flowershop.dto.model.AbstractSearchCriteriaAndSortParamsDto;
import by.bsuir.flowershop.dto.model.PageWrapper;

public interface FindAllPageableSearchableService<T> {

    PageWrapper<T> findAll(int page, int size, AbstractSearchCriteriaAndSortParamsDto searchParams);
}
