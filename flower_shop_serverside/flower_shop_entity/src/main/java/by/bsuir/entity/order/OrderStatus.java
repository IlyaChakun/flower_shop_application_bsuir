package by.bsuir.entity.order;

import lombok.Getter;

@Getter
public enum OrderStatus {

    DRAFT("DRAFT"),
    NEW("NEW"),
    //    PERFORMED("PERFORMED"),
    COMPLETED("COMPLETED"),

    CONFIRMED_BY_CLIENT("CONFIRMED_BY_CLIENT"),
    CONFIRMED_BY_COMPANY("CONFIRMED_BY_COMPANY"),
    REJECTED_BY_CLIENT("REJECTED_BY_CLIENT"),
    REJECTED_BY_COMPANY("REJECTED_BY_COMPANY");

    private final String statusName;

    OrderStatus(String statusName) {
        this.statusName = statusName;
    }
}
