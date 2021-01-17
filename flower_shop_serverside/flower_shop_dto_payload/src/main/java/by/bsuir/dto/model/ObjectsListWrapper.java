package by.bsuir.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ObjectsListWrapper<T> {

    private final List<T> objects;

}
