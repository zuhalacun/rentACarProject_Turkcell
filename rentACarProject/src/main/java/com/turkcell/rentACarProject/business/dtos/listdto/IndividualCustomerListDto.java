package com.turkcell.rentACarProject.business.dtos.listdto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerListDto {

	private int userId;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String nationalIdentity;
	private List<CarRentalListDto> carRentals;

}
