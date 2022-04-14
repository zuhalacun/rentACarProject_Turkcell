package com.turkcell.rentACarProject.business.dtos.dto;

import java.util.List;

import com.turkcell.rentACarProject.business.dtos.listdto.CarRentalListDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerDto {

	private int userId;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String nationalIdentity;
	private List<CarRentalListDto> carRentals;

}
