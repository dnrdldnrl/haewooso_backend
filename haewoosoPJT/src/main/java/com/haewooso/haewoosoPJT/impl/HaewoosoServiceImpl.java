package com.haewooso.haewoosoPJT.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haewooso.haewoosoPJT.dao.HaewoosoDAO;
import com.haewooso.haewoosoPJT.domain.HaewoosoSendMsgVO;
import com.haewooso.haewoosoPJT.domain.HaewoosoUserTableVO;
import com.haewooso.haewoosoPJT.service.HaewoosoService;

@Service
public class HaewoosoServiceImpl implements HaewoosoService {
	
	@Autowired
	HaewoosoDAO haewoosoDAO;
	
	@Override
	public HaewoosoUserTableVO getUUIDAndTOKEN(String uuid) {
		return haewoosoDAO.getUUIDAndTOKEN(uuid);
	}

	@Override
	public HaewoosoUserTableVO getRandomReceiverToken(String myUUID) {
		return haewoosoDAO.getRandomReceiverToken(myUUID);
	}

	@Override
	public void insertMessageInfomation(HaewoosoSendMsgVO vo) {
		haewoosoDAO.getRandomReceiverToken(vo);
	}
	
	
	
}
