package by.bsuir.service.core.base;

public interface SaveUpdateService<T> {

    T save(T t);

    T update(T t);
}
