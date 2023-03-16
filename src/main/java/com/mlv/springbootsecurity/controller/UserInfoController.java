package com.mlv.springbootsecurity.controller;

import com.mlv.springbootsecurity.entity.UserInfo;
import com.mlv.springbootsecurity.service.UserInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@EnableMethodSecurity
public class UserInfoController {

    private UserInfoService userInfoService;
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/hello")
    public String welcome(){
        return "Welcome to security";
    }

    @PostMapping("/add")
    public UserInfo addUser(@RequestBody UserInfo userInfo){

        return userInfoService.saveUser(userInfo);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')") //only admin can access this method
    public List<UserInfo> userList(){
        return userInfoService.userList();
    }

    @GetMapping("/getById")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public Optional<UserInfo> getById(@RequestParam(value="id") Integer id){
        return userInfoService.getUserById(id);
    }


}
