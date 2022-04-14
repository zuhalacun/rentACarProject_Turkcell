package com.turkcell.rentACarProject.business.requests.creates;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRentalRequest {
	
	    @NotBlank
	    private String rentDescription;
	    private LocalDate rentalDate;
	    private LocalDate rentalReturnDate;

	    @NotNull
	    private int carId;
}
