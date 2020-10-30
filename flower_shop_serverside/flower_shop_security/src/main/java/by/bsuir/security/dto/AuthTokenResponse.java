package by.bsuir.security.dto;

import by.bsuir.security.core.SecurityContextConstants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthTokenResponse {

    private final String accessToken;

    private final String refreshToken;

    private final Long expiresIn;

    private String tokenType = SecurityContextConstants.VALID_TOKEN_TYPE.getValue();


}
