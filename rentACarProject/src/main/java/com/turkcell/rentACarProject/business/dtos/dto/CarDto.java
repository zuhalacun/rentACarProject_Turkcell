package com.turkcell.rentACarProject.business.dtos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
	    
	    private int carId;
	    private double carDailyPrice;
	    private int carModelYear;
	    private String carDescription;
	    private String colorName;
	    private String brandName;

	}


     

