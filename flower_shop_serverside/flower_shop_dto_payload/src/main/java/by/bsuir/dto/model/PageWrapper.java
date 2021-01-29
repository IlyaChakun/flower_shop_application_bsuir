package by.bsuir.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageWrapper<T> {

    private List<T> objects;

    private int totalPages;
    private long totalElements;


}