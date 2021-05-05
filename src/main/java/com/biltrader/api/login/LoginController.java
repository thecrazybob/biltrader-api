package com.biltrader.api.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1")
@AllArgsConstructor
public class LoginController {

    LoginService loginService;

    /**
     * Post request for user login
     * 
     * @param loginRequest
     * @return the result of the login and errors if any
     */
    @PostMapping(path = "login")
    @CrossOrigin(origins = "http://localhost:3000")
    public String login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

    /**
     * Get request for logout
     * 
     * @param id of user to logout
     * @return String result of the login and errors if any
     */
    @GetMapping(path = "logout/{appUserId}")
    public String logout(@PathVariable("appUserId") Long id) {
        return loginService.logout(id);
    }

}
