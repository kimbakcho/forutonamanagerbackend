package com.management.forutona.firebasemessage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.management.forutona.Prefrerance;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class FCMMain {
    FirebaseMessaging messageonj;
    FCMMain(){


    }
    public FirebaseMessaging getFirebaseMessagingInstance(){
        return messageonj;
    };
}
