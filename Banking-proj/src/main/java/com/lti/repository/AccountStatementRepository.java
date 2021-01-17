package com.lti.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.dto.AccountStatementDto;
import com.lti.entity.Account;
import com.lti.entity.Activity;

@Repository
public class AccountStatementRepository extends GenericRepository {

	public List<Activity> fetchAccountStatementOfAccountHolder(AccountStatementDto accountSummaryDto) {

		List<Activity> activities = new ArrayList<Activity>();

		activities = entityManager
				.createQuery(
						"select act from Activity act where act.date between :fromDate  and :toDate and  act.account.internetBanking.customerId = :cusId")
				.setParameter("fromDate", accountSummaryDto.getFromDate())
				.setParameter("toDate", accountSummaryDto.getToDate())
				.setParameter("cusId", accountSummaryDto.getCustomerId()).getResultList();
		
		return activities;
	}

}
