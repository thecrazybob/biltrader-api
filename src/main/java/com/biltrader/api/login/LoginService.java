package com.biltrader.api.login;

import com.biltrader.api.appuser.AppUser;
import com.biltrader.api.appuser.AppUserRepository;
import com.biltrader.api.appuser.AppUserService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
/**
 * Login service class
 */
public class LoginService {

    AppUserRepository appUserRepository;
    AppUserService appUserService;

    /**
     * Login user according to info in the LoginRequest
     * 
     * @param loginRequest conaining email and password
     * @return Result of login and errors if any
     */
    public String login(LoginRequest loginRequest) {

        boolean userExists = appUserRepository.findByEmail(loginRequest.getEmail()).isPresent();

        if (!userExists) {
            throw new IllegalStateException("Invalid email address");
        }

        AppUser user;

        user = appUserRepository.findByEmail(loginRequest.getEmail()).get();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }

        appUserService.loginUser(user);

        return "{ \"message\": \"Successful\", \"isLoggedIn\": \"true\", \"firstName\": \"" + user.getFirstName()
                + "\", \"lastName\": \"" + user.getLastName() + "\" }";
    }

    /**
     * Logout user with given id
     * 
     * @param id of user to logout
     * @return Result of logout and errors if any
     */
    public String logout(Long id) {
        appUserService.logoutUser(id);
        return "Successful";
    }
}
