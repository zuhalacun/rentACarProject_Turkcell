package com.turkcell.rentACarProject.business.dtos.dto;

import java.time.LocalDate;

import com.turkcell.rentACarProject.entities.concretes.City;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRentalDto {
	
	private int rentalId;
    private String rentDescription;
    private LocalDate rentalDate;
    private LocalDate rentalReturnDate;

    private int carId;
    private City pickUpCity;
    private City returnCity;
}
