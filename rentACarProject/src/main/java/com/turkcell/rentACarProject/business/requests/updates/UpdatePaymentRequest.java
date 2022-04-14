package com.turkcell.rentACarProject.business.requests.updates;

import java.time.LocalDate;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentRequest {

	private int id;
	
    private LocalDate paymentDate;

	private double totalPaymentAmount;	
	
	private String paymentType;
}
