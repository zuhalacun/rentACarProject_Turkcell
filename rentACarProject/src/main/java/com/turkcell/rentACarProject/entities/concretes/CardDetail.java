package com.turkcell.rentACarProject.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="card_details")
public class CardDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="card_details_id")
	private int id;
	
	@Column(name="card_no")
	private String cardNo;
	
	@Column(name="card_holder_name")
	private String cardHolderName;
	
	@Column(name="card_year")
	private String year;
	
	@Column(name="card_month")
	private String month;
	
	
	@Column(name="card_cvv")
	private String cvv;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
}
