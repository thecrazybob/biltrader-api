package com.biltrader.api.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1")
@AllArgsConstructor
public class LoginController {

    LoginService loginService;

    @PostMapping(path = "login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

    @GetMapping(path = "logout/{appUserId}")
    public String logout(@PathVariable("appUserId") Long id) {
        return loginService.logout(id);
    }

}
