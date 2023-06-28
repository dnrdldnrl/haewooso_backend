package com.haewooso.haewoosoPJT.domain;

import lombok.Data;

@Data
public class HaewoosoUserTableVO {
	private String uuid;
	private String push_token;
	private int send_count;
	
	@Override
	public String toString() {
		return "{UUID : " + uuid + ",\nPUSH_TOKEN : " + push_token + ",\nSEND_COUNT : " + send_count + "}";
	}
	
	
}
