package com.turkcell.rentACarProject.business.dtos.dto;

import java.time.LocalDate;

import javax.persistence.Column;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private int id;

	private LocalDate paymentDate;
	
	private double totalPaymentAmount;	
	
	private String paymentType;
	
	private int rentalId;
}
