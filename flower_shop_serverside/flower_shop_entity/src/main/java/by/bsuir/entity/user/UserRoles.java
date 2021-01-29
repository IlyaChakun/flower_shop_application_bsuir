package by.bsuir.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

//@Getter
//@AllArgsConstructor
//public enum UserRoles {
//
//    ROLE_ADMIN("ROLE_ADMIN"),
//    ROLE_CLIENT("ROLE_CLIENT"),
//    ROLE_STOCK_OWNER("ROLE_STOCK_OWNER"),
//    ROLE_STOCK_WORKER("ROLE_STOCK_WORKER");
//
//    ////////////////////////////////////
//    private final String roleName;
//
//}
@Getter
@AllArgsConstructor
public class UserRoles {

    public static final String ROLE_ADMIN = ("ROLE_ADMIN");
    public static final String ROLE_CLIENT = ("ROLE_CLIENT");
    public static final String ROLE_STOCK_OWNER = ("ROLE_STOCK_OWNER");
    public static final String ROLE_STOCK_WORKER = ("ROLE_STOCK_WORKER");

    ////////////////////////////////////
    //private final String roleName;

}