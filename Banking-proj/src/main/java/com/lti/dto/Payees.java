package com.lti.dto;

import java.util.List;

import com.lti.entity.Beneficiary;

public class Payees {

	private List<Payee> beneficiaries;

	public List<Payee> getBeneficiaries() {
		return beneficiaries;
	}

	public void setBeneficiaries(List<Payee> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}
	
}
