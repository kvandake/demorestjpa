package com.example.controller;


import com.example.model.UserApi;
import com.example.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by home on 29.01.16.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Async
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private UserApi createUser(@RequestBody UserApi userApi, Authentication authentication) throws SecurityException {
        return userService.create(userApi, authentication);
    }

    @Async
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private UserApi getUser(@RequestHeader("email") String email, @RequestHeader("password") String password) throws SecurityException, HttpRequestMethodNotSupportedException {
        return userService.get(email, password);
    }

    @Async
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private UserApi updateUser(@RequestBody UserApi userApi, @RequestHeader("email") String email, @RequestHeader("password") String password) throws SecurityException {
        return userService.update(email, password, userApi);
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    private boolean deleteUser(@RequestParam Map<String, String> params) throws SecurityException {
        return userService.delete(params);
    }
}
