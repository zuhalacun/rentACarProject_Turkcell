package com.turkcell.rentACarProject.business.dtos.listdto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerListDto {

	private int userId;
	private String email;
	private String password;
	private List<CarRentalListDto> CarRentals;

}
