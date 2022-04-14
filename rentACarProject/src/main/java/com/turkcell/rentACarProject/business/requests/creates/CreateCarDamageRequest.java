package com.turkcell.rentACarProject.business.requests.creates;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageRequest {
  
	@NotBlank
	private String description;
	
	@NotNull
	private int carId;
}
