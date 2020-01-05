package com.management.forutona.FCMController;

import com.google.firebase.messaging.*;
import com.management.forutona.FireBaseAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FCMController {

    @PostMapping(value = "/api/v1/FCM/sendMessage")
    void sendMessage(@RequestParam  String title,@RequestParam String body) {
        try{
            String registrationToken = "fsHatBr4UHY:APA91bExwv796_N635C04coaxvLMgz9eYZw1n1DsFACW3ZVqk4ymhI7Cn_Nq-MSV9uBhPqfWaLZYZSlVh3BH3S9WB-cv69ONf_B2dy1uA0J-9KhAeWMIkcUaSAPtUvMXZshcnEn_qyTK";
            Message message = Message.builder()
                    .setNotification(new Notification(
                            title,
                            body))
                    .setTopic("co.kr.forutonafront")

                    .build();
            String response = FirebaseMessaging.getInstance().send(message);

            System.out.println("Successfully sent message: " + response);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

}
