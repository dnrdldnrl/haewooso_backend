package com.haewooso.haewoosoPJT.service;

import com.haewooso.haewoosoPJT.domain.HaewoosoSendMsgVO;
import com.haewooso.haewoosoPJT.domain.HaewoosoUserTableVO;

public interface HaewoosoService {
	
	public HaewoosoUserTableVO getUUIDAndTOKEN(String uuid);
	
	public HaewoosoUserTableVO getRandomReceiverToken(String myUUID);
	
	public void insertMessageInfomation(HaewoosoSendMsgVO vo);
}
