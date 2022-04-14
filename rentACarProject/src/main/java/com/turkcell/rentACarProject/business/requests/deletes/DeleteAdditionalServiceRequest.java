package com.turkcell.rentACarProject.business.requests.deletes;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteAdditionalServiceRequest {

	@NotNull
	@Min(1)
	private int additionalServiceId;
}
