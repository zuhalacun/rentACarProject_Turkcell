package com.turkcell.rentACarProject.business.requests.creates;

import java.time.LocalDate;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLateDeliveriesPaymentRequest {

	@Min(1)
    private int rentalCarId;

    private LocalDate delayedReturnDate;
    
    private double carDelayedKilometer;

    private CreatePaymentRequest createPaymentRequest;
}
