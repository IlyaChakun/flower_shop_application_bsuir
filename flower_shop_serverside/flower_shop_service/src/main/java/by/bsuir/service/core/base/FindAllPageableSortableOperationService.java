package by.bsuir.service.core.base;

import by.bsuir.dto.model.AbstractSearchAndSortParamsDto;

import java.util.List;

public interface FindAllPageableSortableOperationService<T> {

    List<T> findAll(int page, int size, AbstractSearchAndSortParamsDto searchParams);
}
