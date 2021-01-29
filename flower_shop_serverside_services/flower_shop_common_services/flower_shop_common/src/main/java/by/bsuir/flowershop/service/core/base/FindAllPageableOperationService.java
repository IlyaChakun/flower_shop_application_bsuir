package by.bsuir.flowershop.service.core.base;

import by.bsuir.flowershop.dto.model.PageWrapper;

public interface FindAllPageableOperationService<T> {

    PageWrapper<T> findAll(int page, int size);
}
