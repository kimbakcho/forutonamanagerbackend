package com.management.forutona;

import com.management.forutona.AuthController.AuthController;
import com.management.forutona.AuthDto.Userinfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ForutonaApplicationTests {

    @Autowired
    AuthController controller;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        System.out.println(passwordEncoder.encode("forutona"));

    }

}
