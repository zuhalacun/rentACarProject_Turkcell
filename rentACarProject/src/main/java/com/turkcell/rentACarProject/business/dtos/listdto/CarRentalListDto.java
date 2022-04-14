package com.turkcell.rentACarProject.business.dtos.listdto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRentalListDto {
    
	private int rentalId;
	private String rentDescription;
    private LocalDate rentalDate;
    private LocalDate rentalReturnDate;

    private int carId;
}
