package com.turkcell.rentACarProject.business.dtos.listdto;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedAdditionalServiceListDto {

	private int id;
	private int rentalId;
	private int additionalServiceId;
}
