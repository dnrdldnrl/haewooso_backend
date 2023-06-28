package com.haewooso.haewoosoPJT.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.haewooso.haewoosoPJT.service.FCMNotificationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FCMNotificationServiceImpl implements FCMNotificationService  {

    private final FirebaseMessaging firebaseMessaging;

    @Override
     public  ResponseEntity<String> sendNotificationByToken (String title, String desc, String myUUID, String receiverToken)   {


                Notification notification = Notification.builder()
                        .setTitle(title)
                        .setBody(desc)
                         // .setImage(requestDto.getImage()) 
                        .build();

                Message message = Message.builder()
                        .setToken(receiverToken)
                        .setNotification(notification)
                         // .putAllData(requestDto.getData()) 
                        .build();

                try  {
                    firebaseMessaging.send(message);
                    return new ResponseEntity<String>("200",HttpStatus.OK);
                } catch (FirebaseMessagingException e) {
                    e.printStackTrace();
                    return new ResponseEntity<String>("400", HttpStatus.BAD_REQUEST);
                }




    }

}