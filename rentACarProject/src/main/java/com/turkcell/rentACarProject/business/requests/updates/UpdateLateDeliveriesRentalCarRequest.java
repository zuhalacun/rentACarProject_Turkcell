package com.turkcell.rentACarProject.business.requests.updates;

import com.turkcell.rentACarProject.business.requests.creates.CreatePaymentRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLateDeliveriesRentalCarRequest {

    private UpdateCarRentalRequest updateCarRentalRequest;
	
	private CreatePaymentRequest createPaymentRequest;
}
