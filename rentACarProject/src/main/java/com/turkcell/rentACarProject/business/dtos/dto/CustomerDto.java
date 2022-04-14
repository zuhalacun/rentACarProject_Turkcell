package com.turkcell.rentACarProject.business.dtos.dto;

import java.util.List;

import com.turkcell.rentACarProject.business.dtos.listdto.CarRentalListDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

	private int userId;
	private String email;
	private String password;
	private List<CarRentalListDto> carRentals;

}
