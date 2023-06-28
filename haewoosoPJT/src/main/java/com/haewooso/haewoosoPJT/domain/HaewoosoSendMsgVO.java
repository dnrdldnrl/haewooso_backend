package com.haewooso.haewoosoPJT.domain;

import java.util.Date;

import lombok.Data;

@Data
public class HaewoosoSendMsgVO {
	private String sender_uuid;
	private String receiver_uuid;
	private String title;
	private String description;
	private Date send_dtm;
	private Date receive_dtm;
}
