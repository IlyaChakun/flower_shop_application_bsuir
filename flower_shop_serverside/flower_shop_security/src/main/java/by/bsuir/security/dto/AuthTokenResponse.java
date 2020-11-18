package by.bsuir.security.dto;

import by.bsuir.security.core.SecurityContextConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthTokenResponse {

    private final String accessToken;

    private final String refreshToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH.mm")
    private final Date expireDate;

    private String tokenType = SecurityContextConstants.VALID_TOKEN_TYPE.getValue();


}
