package com.biltrader.api.login;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
/**
 * LoginRequest contining email and password
 */
public class LoginRequest {
    private String email;
    private String password;
}