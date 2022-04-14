package com.turkcell.rentACarProject.business.requests.updates;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarDamageRequest {

	@NotNull
    private int id;
	
	@NotBlank
    private String description;
}
