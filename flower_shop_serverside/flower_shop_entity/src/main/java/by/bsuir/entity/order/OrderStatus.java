package by.bsuir.entity.order;

import lombok.Getter;

@Getter
public enum OrderStatus {

    NEW("NEW"),
    IN_PROCESS("IN_PROCESS"),
    COMPLETED("COMPLETED");

    private final String statusName;

    OrderStatus(String statusName) {
        this.statusName = statusName;
    }
}