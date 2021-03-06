package com.turkcell.rentACarProject.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cars")
public class Car {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name= "car_id")
        private int id;

	    @Column(name= "car_dailyprice")
	    private double carDailyPrice;

	    @Column(name= "car_modelyear")
	    private int carModelYear;

	    @Column(name= "car_description")
	    private String carDescription;

	    @Column(name="car_kilometer")
	    private int carKilometer;
	    
	    @ManyToOne
	    @JoinColumn(name = "brand_id")
	    private Brand brand;

	    @ManyToOne
	    @JoinColumn(name = "color_id")
	    private Color color;

        @ManyToOne
	    @JoinColumn(name = "city_id")
		private City city;
	    
	    @OneToMany(mappedBy = "car")
	    private List<CarMaintenance> carMaintenances;

	    @OneToMany(mappedBy = "car")
	    private List<CarRental> carRentals;
	   
	    @OneToMany(mappedBy = "car")
	    private List<CarDamage> carDamages;
	}

