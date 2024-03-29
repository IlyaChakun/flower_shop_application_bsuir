package by.bsuir.service.core.base;

import by.bsuir.dto.model.AbstractSearchCriteriaAndSortParamsDto;
import by.bsuir.dto.model.PageWrapper;

public interface FindAllPageableSortableWithSearchCriteriaOperationService<T> {

    PageWrapper<T> findAll(int page, int size, AbstractSearchCriteriaAndSortParamsDto searchParams);
}
