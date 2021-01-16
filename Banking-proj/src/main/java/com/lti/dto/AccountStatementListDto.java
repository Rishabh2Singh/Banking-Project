package com.lti.dto;

import java.util.List;

import com.lti.entity.Activity;

public class AccountStatementListDto {

	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private List<AccountStatementResDto> resDto;
	
	public List<AccountStatementResDto> getResDto() {
		return resDto;
	}

	public void setResDto(List<AccountStatementResDto> resDto) {
		this.resDto = resDto;
	}
}
