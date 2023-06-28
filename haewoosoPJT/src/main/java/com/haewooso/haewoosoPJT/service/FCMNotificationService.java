package com.haewooso.haewoosoPJT.service;

import org.springframework.http.ResponseEntity;

public interface FCMNotificationService {
	
	public ResponseEntity<String> sendNotificationByToken(String title, String desc, String myUUID, String receiverToken);
	
}
