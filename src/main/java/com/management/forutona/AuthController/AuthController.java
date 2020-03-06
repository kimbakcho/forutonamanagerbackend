package com.management.forutona.AuthController;

import com.google.firebase.auth.UserInfo;
import com.management.forutona.AuthDao.AuthDao;
import com.management.forutona.AuthDto.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    AuthDao authdao;



}
