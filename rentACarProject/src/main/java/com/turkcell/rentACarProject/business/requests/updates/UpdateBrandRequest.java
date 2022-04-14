package com.turkcell.rentACarProject.business.requests.updates;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandRequest {
	   
	   @NotNull
	   private int brandId;	
	   @NotBlank
	   @Size(min=2,max=20)
	   private String brandName;
}
