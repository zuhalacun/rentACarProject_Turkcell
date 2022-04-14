package com.turkcell.rentACarProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentACarProject.business.abstracts.CarDamageService;
import com.turkcell.rentACarProject.business.dtos.dto.CarMaintenanceDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CarDamageListDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CarMaintenanceListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCarDamageRequest;
import com.turkcell.rentACarProject.business.requests.creates.CreateCarMaintenanceRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCarDamageRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCarMaintenanceRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCarDamageRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCarMaintenanceRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

@RestController
@RequestMapping("/api/cardamages")
public class CarDamageController {

	private CarDamageService carDamageService;

	@Autowired
	public CarDamageController(CarDamageService carDamageService) {
		this.carDamageService = carDamageService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<CarDamageListDto>> getAll() throws BusinessException{
		
		return this.carDamageService.getAll();
	}
	
	@GetMapping("/getByCarId")
	public DataResult<List<CarDamageListDto>> getByCarId(@RequestParam int id)throws BusinessException{
		
		return this.carDamageService.getByCarId(id);
	}
	
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarDamageRequest createCarDamageRequest) throws BusinessException{
		
		return this.carDamageService.add(createCarDamageRequest);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateCarDamageRequest updateCarDamageRequest)throws BusinessException {
		
		return this.carDamageService.update(updateCarDamageRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteCarDamageRequest deleteCarDamageRequest) throws BusinessException{
		
		return this.carDamageService.delete(deleteCarDamageRequest);
	}
}
