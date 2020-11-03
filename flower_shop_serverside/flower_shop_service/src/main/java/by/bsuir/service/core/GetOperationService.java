package by.bsuir.service.core;

import java.util.List;
import java.util.Optional;

public interface GetOperationService<T> {

    T getOne(Long id);
}
