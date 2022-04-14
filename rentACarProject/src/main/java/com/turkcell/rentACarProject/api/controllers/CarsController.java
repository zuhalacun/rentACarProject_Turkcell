package com.turkcell.rentACarProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentACarProject.business.abstracts.CarService;
import com.turkcell.rentACarProject.business.dtos.dto.CarDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CarListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCarRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCarRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCarRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
	
	private CarService carService;

	@Autowired
	public CarsController(CarService carService) {

		this.carService = carService;
	}

	@GetMapping("/getAllPaged/{pageNumber}/{pageSize}")
	public DataResult<List<CarListDto>> getAllPaged(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {

		return this.carService.getAllPaged(pageNumber, pageSize);
	}

	@GetMapping("/getAllSorted/{orderOfSort}")
	public DataResult<List<CarListDto>> getAllSorted(@RequestParam("orderOfSort") String orderOfSort) throws BusinessException {

		return this.carService.getAllSorted(orderOfSort);
	}
	
	@GetMapping("/getByCarId/{carId}")
	 
	public DataResult<CarDto> getByCarId(@PathVariable("carId") int carId) throws BusinessException {
		return this.carService.getByCarId(carId);
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) throws BusinessException {

		return this.carService.add(createCarRequest);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) throws BusinessException {
		
		return this.carService.update(updateCarRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteCarRequest deleteCarRequest) throws BusinessException {
		
		return this.carService.delete(deleteCarRequest);
	}
}
