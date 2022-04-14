package com.turkcell.rentACarProject.business.requests.creates;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalServiceRequest {
	
	@NotBlank
	@Size(min = 3, max = 50)
	private String additionalServiceName;
	
	@NotNull
	@Min(100)
	private double dailyPrice;
}
