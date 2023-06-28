package com.haewooso.haewoosoPJT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.haewooso.haewoosoPJT.domain.HaewoosoSendMsgVO;
import com.haewooso.haewoosoPJT.domain.HaewoosoUserTableVO;
import com.haewooso.haewoosoPJT.service.FCMNotificationService;
import com.haewooso.haewoosoPJT.service.HaewoosoService;

@RestController
public class HaewoosoController {
	
	@Autowired
	HaewoosoService haewoosoService;
	
	@Autowired
	FCMNotificationService fFCMNotificationService; 
	
	
	/* GetMapping은 기본적으로 @ResponseBody 어노테이션을 내장하고 있어서 명시를 안해줘도 ResponseBody 기능을 수행한다. */
	@GetMapping("/v1/test") 
	public HaewoosoUserTableVO testHaewoosoData() {
		
		System.out.println("test!");
				
		/* UUID 값, 화면단에서 받아왔다고 가정 */
		String uuid = "c1abf09f-3374-4c77-99e7-c6c4c86a3422";
				
		HaewoosoUserTableVO result = haewoosoService.getUUIDAndTOKEN(uuid);
		
		
		/* 이 프로젝트에서 back단의 모든 return 형식은 JSON 형식임. */
		return result;
	}
	
	/* v1/push 요청이 왔을때 post 방식으로 데이터 받음, PostMapping은 @ResponseBody 명시해주어야함. */
	@PostMapping("/v1/push")
	@ResponseBody
	public String pushAlarmMessage(@RequestBody HaewoosoSendMsgVO pushDataVO) {
		
		String title = pushDataVO.getTitle();
		String desc = pushDataVO.getDescription();
		/* 푸쉬알람 보내는 사람의 UUID (sender) -> 보내는 사람이니까 본인 UUID임. */
		String myUUID = pushDataVO.getSender_uuid();

		/* 프론트단에서 받아온 데이터들 잘 받았는지 출력문 찍어보기 */
		System.out.println(title);
		System.out.println(desc);
		System.out.println(myUUID);
		
		/* 푸쉬알람 받는사람의 UUID를 랜덤으로 조회 (receiver) -> 그리고 랜덤으로 나온 사람의 토큰값 가져와야함. */ 
		String randomReceiverToken = haewoosoService.getRandomReceiverToken(myUUID).getPush_token();		
		//String randomReceiverToken = "dKOYein2eEnggTxtYUr_3b:APA91bHP1iK2UTZ9PCu-lcbqosZsMbodxHfLKzFALzmUwocPTBMFE1EP3nJrF1PMoUOVkgSF796xoMVwtAoycUGYFVTB3eec500htoTup0JxaFQZSePxa_nVFDjpfz9IU-5jYQrQcEXd";
		
		System.out.println(randomReceiverToken);
		pushDataVO.setReceiver_uuid(randomReceiverToken);
		
		/* 
		 * 프론트단에서 받아온 파라미터와 DB에서 조회해온 데이터들을 토대로 파이어베이스 푸쉬알람 로직 구현 
		 * myUUID -> 답장 받을때 필요하니까 myUUID도 파라미터로 보냄.
		 * 
		 * */
		String result = fFCMNotificationService.sendNotificationByToken(title, desc, myUUID, randomReceiverToken).getBody();
		
		if("200".equals(result)) {
			haewoosoService.insertMessageInfomation(pushDataVO);
		}

		
		
		
		/* 파이어베이스 푸쉬알람 기능 수행 후 결과값을 리턴 (예를들어 성공이면 200, 실패 400 이런 값들을 프톤트단에 리턴 */
		return result;
	}
	
	
}
