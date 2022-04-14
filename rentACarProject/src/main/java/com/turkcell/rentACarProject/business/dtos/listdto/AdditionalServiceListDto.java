package com.turkcell.rentACarProject.business.dtos.listdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServiceListDto {

	private int id;
	private String additionalServiceName;
	private double dailyPrice;
}
