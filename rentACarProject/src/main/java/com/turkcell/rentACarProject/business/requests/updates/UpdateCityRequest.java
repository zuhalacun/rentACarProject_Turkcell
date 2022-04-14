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
public class UpdateCityRequest {

	@NotNull
	@Min(1)
    private int cityId;
	
	@NotBlank
	@Size(min = 2, max = 50)
    private String cityName;


}
