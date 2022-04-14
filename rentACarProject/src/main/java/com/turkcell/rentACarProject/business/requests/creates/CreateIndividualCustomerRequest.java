package com.turkcell.rentACarProject.business.requests.creates;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {

	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Size(min = 8, max = 20)
	private String password;
	
	@NotBlank
	@Size(min = 2, max = 40)
	private String firstName;
	
	@NotBlank
	@Size(min = 2, max = 40)
	private String lastName;
	
	@NotBlank
	@Size(min = 11, max = 11)
	private String nationalIdentity;

}
