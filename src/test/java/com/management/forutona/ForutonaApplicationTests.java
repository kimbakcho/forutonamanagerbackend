package com.management.forutona;

import com.management.forutona.AuthController.AuthController;
import com.management.forutona.AuthDto.Userinfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ForutonaApplicationTests {

    @Autowired
    AuthController controller;

    @Test
    void contextLoads() {
        Userinfo userinfo =new Userinfo();
        userinfo.setUid("forutona");
        userinfo.setPassword("12345678");
        Userinfo item = controller.Login(userinfo);
        System.out.println(item.getAccesstoken());
    }

}
