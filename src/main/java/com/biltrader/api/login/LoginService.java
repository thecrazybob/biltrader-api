package com.biltrader.api.login;

import com.biltrader.api.appuser.AppUser;
import com.biltrader.api.appuser.AppUserRepository;
import com.biltrader.api.appuser.AppUserService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginService {

    AppUserRepository appUserRepository;
    AppUserService appUserService;

    public String login(LoginRequest loginRequest) {

        boolean userExists = appUserRepository.findByEmail(loginRequest.getEmail()).isPresent();

        if (!userExists) {
            throw new IllegalStateException("invalid email adress");
        }

        AppUser user;

        user = appUserRepository.findByEmail(loginRequest.getEmail()).get();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalStateException("wrong password");
        }

        appUserService.loginUser(user);

        return "successful";
    }

    public String logout(Long id) {
        appUserService.logoutUser(id);
        return "successfull";
    }
}