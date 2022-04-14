package com.turkcell.rentACarProject.business.dtos.listdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListDto {
   
	private int carId;
	private double carDailyPrice;
    private int carModelYear;
    private String carDescription;
    private String colorName;
    private String brandName;
}
