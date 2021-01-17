package by.bsuir.service.core.base;

import java.util.List;

public interface FindAllPageableOperationService<T> {

    List<T> findAll(int page, int size);
}
