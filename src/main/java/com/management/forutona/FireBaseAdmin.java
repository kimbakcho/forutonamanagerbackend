package com.management.forutona;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

@Component
public class FireBaseAdmin {

    FirebaseApp admin;

    FireBaseAdmin() {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream(Prefrerance.serviekeypath);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://forutona.firebaseio.com")
                    .build();

            admin = FirebaseApp.initializeApp(options);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    public FirebaseApp getFireBaseAdminInstance(){
        return admin;
    }
}
