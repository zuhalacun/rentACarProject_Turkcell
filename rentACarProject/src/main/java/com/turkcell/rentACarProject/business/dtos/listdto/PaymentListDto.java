package com.turkcell.rentACarProject.business.dtos.listdto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentListDto {

	    private int id;

		private LocalDate paymentDate;
		
		private double totalPaymentAmount;	
		
		private String paymentType;
		
		private int rentalId;
}
