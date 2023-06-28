package com.haewooso.haewoosoPJT.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.haewooso.haewoosoPJT.domain.HaewoosoSendMsgVO;
import com.haewooso.haewoosoPJT.domain.HaewoosoUserTableVO;

@Repository
public class HaewoosoDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public HaewoosoUserTableVO getUUIDAndTOKEN(String uuid) {
		
		return mybatis.selectOne("HaewoosoMapper.selectUUIDAndTOKEN", uuid);
		
	}
	
	public HaewoosoUserTableVO getRandomReceiverToken(String myUUID) {
		
		return mybatis.selectOne("HaewoosoMapper.selectRandomReceiverToken", myUUID);
		
	}
	
	public void getRandomReceiverToken(HaewoosoSendMsgVO vo) {
		
		mybatis.insert("insertMessageInformaion", vo);
		
		
	}
	
}
