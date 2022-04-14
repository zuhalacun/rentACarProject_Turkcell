package com.turkcell.rentACarProject.business.requests.creates;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {

    private LocalDate paymentDate;

	private double totalPaymentAmount;	
	
	private String paymentType;
	
	private String cardNo; 
	private String day; 
	private String month; 
	private String cvv;
	private int rentalId;
}
