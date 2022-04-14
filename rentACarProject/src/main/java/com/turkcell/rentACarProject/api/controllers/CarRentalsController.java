package com.turkcell.rentACarProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.turkcell.rentACarProject.business.abstracts.CarRentalService;
import com.turkcell.rentACarProject.business.dtos.dto.CarRentalDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CarRentalListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCarRentalRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCarRentalRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCarRentalRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

@RestController
@RequestMapping("/api/carrentals")
public class CarRentalsController {
	
	private CarRentalService carRentalService;

	@Autowired
    public CarRentalsController(CarRentalService carRentalService) {
		this.carRentalService = carRentalService;
	}

    @GetMapping("/getall")
	DataResult<List<CarRentalListDto>> getAll() throws BusinessException {

		return this.carRentalService.getAll();
	}

	@GetMapping("/getByCarId")
	List<CarRentalListDto> getByCarId(int id) throws BusinessException {

		return this.carRentalService.getByCarId(id);
	}
	
	@GetMapping("/getByRentalId")
	DataResult<CarRentalDto> getByRentalId(int id) throws BusinessException{
		
		return this.carRentalService.getByRentalId(id);
	}
	
	@PostMapping("add-for-individual-customer")
	public Result addForIndividualCustomer(@RequestBody @Valid CreateCarRentalRequest createCarRentalRequest) throws BusinessException {
		return this.carRentalService.addForIndividualCustomer(createCarRentalRequest);
	}

	@PostMapping("add-for-corporate-customer")
	public Result addForCorporateCustomer(@RequestBody @Valid CreateCarRentalRequest createCarRentalRequest) throws BusinessException {
		return this.carRentalService.addForCorporateCustomer(createCarRentalRequest);

	}
	@PutMapping("/update")
	Result update(@RequestBody @Valid UpdateCarRentalRequest updateCarRentalRequest) throws BusinessException{

		return this.carRentalService.update(updateCarRentalRequest);
	}

	@DeleteMapping("/delete")
	Result delete(@RequestBody @Valid DeleteCarRentalRequest deleteCarRentalRequest) throws BusinessException {

		return this.carRentalService.delete(deleteCarRentalRequest);
	}
	
}
