package com.turkcell.rentACarProject.business.dtos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDetailDto {

	private int id;
	private String cardNo;
		
		
		private String cardHolderName;
		
		
		private String year;
		

		private String month;
		
		
		private String cvv;
		private int userId;
}
