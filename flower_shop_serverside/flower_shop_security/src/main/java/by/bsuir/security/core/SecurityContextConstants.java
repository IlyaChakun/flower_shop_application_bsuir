package by.bsuir.security.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SecurityContextConstants {

    TOKEN_TYPE("Token-Type"),
    AUTHORIZATION_ACCESS_TOKEN("Authorization-Access-Token"),
    AUTHORIZATION_REFRESH_TOKEN("Authorization-Refresh-Token"),

    GRANT_TYPE("Grant-Type"),
    GRANT_TYPE_REFRESH_TOKEN("refresh_token"),
    GRANT_TYPE_ANON_ACTION("anon_action"),
    GRANT_TYPE_ACTION("action"),

    VALID_TOKEN_TYPE("bearer");

    private final String value;

}