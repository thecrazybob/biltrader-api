package com.biltrader.api.appuser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/app_user")
@AllArgsConstructor
public class AppUserController {

    AppUserService appUserService;

    @GetMapping(path = "{appUserId}")
    public AppUser getAppUser(@PathVariable("appUserId") Long id) {
        return appUserService.getUserById(id);
    }
}
