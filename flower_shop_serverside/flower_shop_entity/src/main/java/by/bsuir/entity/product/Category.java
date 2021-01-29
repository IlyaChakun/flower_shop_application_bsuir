package by.bsuir.entity.product;

import by.bsuir.entity.BaseAbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category extends BaseAbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "parent_id")
    private Integer parentId;

    @ManyToMany
    private List<Category> children = new ArrayList<>();

}
