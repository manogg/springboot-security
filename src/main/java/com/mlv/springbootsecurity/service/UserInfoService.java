package com.mlv.springbootsecurity.service;

import com.mlv.springbootsecurity.entity.UserInfo;
import com.mlv.springbootsecurity.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserInfoService() {
    }

    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfo saveUser(UserInfo userInfo){

        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));

        return userInfoRepository.save(userInfo);
    }

    public List<UserInfo> userList(){

        List<UserInfo> list=new ArrayList<>();

        list=userInfoRepository.findAll();

        return list;
    }

    public Optional<UserInfo> getUserById(Integer id){

        Optional<UserInfo> getById=userInfoRepository.findById(id);

        return getById;
    }
}
