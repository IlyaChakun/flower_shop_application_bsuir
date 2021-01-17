package by.bsuir.service.core.base;

import by.bsuir.dto.model.AbstractSearchAndSortParamsDto;
import by.bsuir.dto.model.PageWrapper;

import java.util.List;

public interface FindAllPageableSortableOperationService<T> {

    PageWrapper<T> findAll(int page, int size, AbstractSearchAndSortParamsDto searchParams);
}
