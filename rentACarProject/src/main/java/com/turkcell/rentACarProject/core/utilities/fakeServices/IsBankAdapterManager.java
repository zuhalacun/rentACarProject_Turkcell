package com.turkcell.rentACarProject.core.utilities.fakeServices;

import org.springframework.stereotype.Service;

import com.turkcell.rentACarProject.core.utilities.externalServices.IsBank;
import com.turkcell.rentACarProject.core.utilities.results.result.ErrorResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;
import com.turkcell.rentACarProject.core.utilities.results.result.SuccessResult;

@Service
public class IsBankAdapterManager implements BankAdapterService{

	@Override
	public Result checkIfLimitIsEnough(String cardNo, String day, String mounth, String cVV, double amount) {
		
		IsBank isBank = new IsBank();
		if( isBank.isLimitExists(cardNo,day,mounth,cVV,amount)) {
			return new SuccessResult();
		}else {	
			return new ErrorResult("limit yeterli değil");
			}
	}

}
