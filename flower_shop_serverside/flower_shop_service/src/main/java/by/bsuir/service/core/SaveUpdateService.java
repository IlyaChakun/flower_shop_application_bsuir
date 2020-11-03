package by.bsuir.service.core;

public interface SaveUpdateService<T> {

    T save(T t);

    T update(T t);
}
