package com.turkcell.rentACarProject.business.dtos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServiceDto {

	private int id;
	private String additionalServiceName;
	private double dailyPrice;
}
