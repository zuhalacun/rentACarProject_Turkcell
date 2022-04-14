package com.turkcell.rentACarProject.business.dtos.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {

	private int invoiceId;

	private String invoiceNumber;

	private LocalDate creationDate;

	private LocalDate rentalDate;

	private LocalDate rentalReturnDate;

	private int totalRentDay;

	private double totalPrice;

	private int customerUserId;

	private int rentalId;

}
