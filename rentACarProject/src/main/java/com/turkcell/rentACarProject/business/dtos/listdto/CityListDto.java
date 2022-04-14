package com.turkcell.rentACarProject.business.dtos.listdto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityListDto {

	private int cityId;
	private String cityName;
}
