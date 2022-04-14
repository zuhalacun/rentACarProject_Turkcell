package com.turkcell.rentACarProject.business.requests.updates;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
	
	@NotNull
	private int carId;
	@NotNull
    private double carDailyPrice;
	@NotNull
    private int carModelYear;
	@NotBlank
    private String carDescription;
	@NotNull
    private int colorId;
	@NotNull
    private int brandId;
}
