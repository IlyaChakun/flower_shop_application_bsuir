package by.bsuir.repository.api.core;


import by.bsuir.entity.BaseAbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<T extends BaseAbstractEntity>
        extends JpaRepository<T, Long> {

}