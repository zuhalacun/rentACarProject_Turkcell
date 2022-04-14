package com.turkcell.rentACarProject.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customers")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="user_id")
public class Customer extends User{
	
	@Column(name="customer_id")
	private int id;
	
	@OneToMany(mappedBy = "customer")
	private List<CarRental> rentals;

	@OneToMany(mappedBy = "customer")
	private List<Invoice> invoices;
}
