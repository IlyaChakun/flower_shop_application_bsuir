package by.bsuir.service.core;

import java.util.List;
import java.util.Optional;

public interface SearchCrudService<T> {

    List<T> findAll();

    Optional<T> findById(Long id);

    T getOne(Long id);
}
