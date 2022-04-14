package com.turkcell.rentACarProject.business.dtos.listdto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceListDto {
	
	private int maintenanceId;
	private String maintenanceDescription;
	private LocalDate returnDate;

    private int carId;
}
