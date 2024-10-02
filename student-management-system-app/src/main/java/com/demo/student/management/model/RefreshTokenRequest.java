package com.demo.student.management.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import static com.demo.student.management.constant.ErrorMessages.REFRESH_TOKEN_BLANK;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRequest {
    @NotBlank(message = REFRESH_TOKEN_BLANK)
    private String refreshToken;
}
