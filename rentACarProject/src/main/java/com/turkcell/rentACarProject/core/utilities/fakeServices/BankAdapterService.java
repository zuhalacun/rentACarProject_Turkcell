package com.turkcell.rentACarProject.core.utilities.fakeServices;


import com.turkcell.rentACarProject.core.utilities.results.result.Result;

public interface BankAdapterService {

	Result checkIfLimitIsEnough(String cardNo,String day,String mounth,String cVV,double amount);

}
