package by.bsuir.service.core.base;

import by.bsuir.dto.model.PageWrapper;

public interface FindAllPageableOperationService<T> {

    PageWrapper<T> findAll(int page, int size);
}
