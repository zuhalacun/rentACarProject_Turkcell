package com.turkcell.rentACarProject.business.requests.deletes;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarRentalRequest {
 
	@NotNull
	private int rentalId;
}
