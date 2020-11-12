package by.bsuir.dto.model;

import java.util.List;

public class PageWrapper<T> {

    private List<T> objects;

    private int totalPages;
    private long totalElements;


    public PageWrapper(List<T> objects, int totalPages, long totalElements) {
        this.objects = objects;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}