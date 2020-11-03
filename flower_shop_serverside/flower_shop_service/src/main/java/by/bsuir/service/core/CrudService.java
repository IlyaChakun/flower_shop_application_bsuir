package by.bsuir.service.core;

public interface CrudService<T> extends SearchCrudService<T> {

    T save(T t);

    T update(T t);

    void delete(Long id);

}
