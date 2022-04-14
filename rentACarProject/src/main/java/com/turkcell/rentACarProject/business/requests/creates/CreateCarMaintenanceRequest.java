package com.turkcell.rentACarProject.business.requests.creates;



import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {
	
	@NotBlank
	@Size(min=2,max=50)
	private String maintenanceDescription;
	private LocalDate returnDate;

	@NotNull
    private int carId;
}
