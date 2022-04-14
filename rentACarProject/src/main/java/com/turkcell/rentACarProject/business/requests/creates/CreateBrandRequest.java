package com.turkcell.rentACarProject.business.requests.creates;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {
	   
	   @NotBlank
	   @Size(min=2,max=20)
	   private String brandName;
}
