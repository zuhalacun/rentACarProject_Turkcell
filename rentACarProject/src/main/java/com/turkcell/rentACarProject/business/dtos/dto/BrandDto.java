package com.turkcell.rentACarProject.business.dtos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {
	
   private int brandId;	
   private String brandName;
}
