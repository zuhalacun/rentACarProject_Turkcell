package com.turkcell.rentACarProject.business.requests.updates;

import java.time.LocalDate;


import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRentalRequest {
	
	@NotNull
	private int rentalId;
	@NotBlank
    private String rentDescription;
    private LocalDate rentalDate;
    private LocalDate rentalReturnDate;
    private int pickUpCityId;
    private int returnCityId;
    private int rentedKilometer;
    private int returnedKilometer;
    
    @NotNull
    private int carId;
}
