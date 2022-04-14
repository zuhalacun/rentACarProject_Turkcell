package com.turkcell.rentACarProject.business.dtos.listdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListDto {

	private int userId;
	private String email;
	private String password;
}
