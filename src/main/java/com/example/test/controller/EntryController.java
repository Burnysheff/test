package com.example.test.controller;

import com.example.test.dto.LoginForm;
import com.example.test.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntryController {
    UserService userService = new UserService();
    @PostMapping("/userCreate")
    @ApiOperation(value = "Saves user")
    public void creator(@RequestBody LoginForm loginForm) {
        userService.saveUser(loginForm);
    }
}
