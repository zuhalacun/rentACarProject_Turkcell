package com.turkcell.rentACarProject.business.requests.creates;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCardDetailRequest {
	
	private String cardNo;
	
	
	private String cardHolderName;
	
	
	private String year;
	

	private String month;
	
	
	private String cvv;
	private int userId;
	
}
