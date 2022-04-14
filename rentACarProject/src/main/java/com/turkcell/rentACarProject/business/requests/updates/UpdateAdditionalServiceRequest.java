package com.turkcell.rentACarProject.business.requests.updates;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalServiceRequest {
	
	@NotNull
	@Min(1)
	private int additionalServiceId;
	
	@NotBlank
	@Size(min = 3, max = 50)
	private String additionalServiceName;
	
	@NotNull
	@Min(1)
	private double dailyPrice;

}
