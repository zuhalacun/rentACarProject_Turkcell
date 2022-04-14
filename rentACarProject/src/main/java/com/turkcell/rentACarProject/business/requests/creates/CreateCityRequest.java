package com.turkcell.rentACarProject.business.requests.creates;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCityRequest {

	@NotBlank
	@Size(min = 2, max = 50)
    private String cityName;
}
