package by.bsuir.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoles {

    ROLE_CLIENT("ROLE_CLIENT"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_ANONYMOUS("ROLE_ANONYMOUS"),
    ROLE_COURIER("ROLE_COURIER");

    ////////////////////////////////////
    private final String roleName;

}
