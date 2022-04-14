package com.turkcell.rentACarProject.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="car_rentals")
public class CarRental {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private int rentalId;

    @Column(name = "rental_description")
    private String rentalDescription;

    @Column(name = "rental_date")
    private LocalDate rentalDate;

    @Column(name = "rental_return_date")
    private LocalDate rentalReturnDate;
    
    @Column(name="rented_kilometer")
	private int rentedKilometer;
    
	@Column(name="returned_kilometer")
	private int returnedKilometer;
    
    @ManyToOne()
	@JoinColumn(name="pickup_city_id")
	private City pickUpCity;
	
	@ManyToOne
	@JoinColumn(name="return_city_id")
	private City returnCity;
    
    @ManyToOne
    @JoinColumn(name="car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
    
    @OneToMany(mappedBy = "carRental")
    private List<OrderedAdditionalService> orderedAdditionalServices;

    @OneToMany(mappedBy = "carRental")
    private List<Invoice> invoices;
    
    @OneToMany(mappedBy = "carRental")
    private List<Payment> payments;
}
