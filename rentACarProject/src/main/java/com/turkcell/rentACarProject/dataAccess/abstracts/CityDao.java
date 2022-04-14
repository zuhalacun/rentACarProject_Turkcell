package com.turkcell.rentACarProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.rentACarProject.entities.concretes.City;
import com.turkcell.rentACarProject.entities.concretes.Color;

@Repository
public interface CityDao extends JpaRepository<City, Integer>{
	
	City getByName(String name);
}
