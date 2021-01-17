package com.lti.service;

import java.util.List;

import com.lti.dto.AccountStatementDto;
import com.lti.dto.AccountStatementResDto;
import com.lti.entity.Activity;

public interface AccountStatementService {

	public List<AccountStatementResDto> accountStatement(AccountStatementDto accountStatement);

	public List<AccountStatementResDto> defaultAccountStatement(int cusId);
}
