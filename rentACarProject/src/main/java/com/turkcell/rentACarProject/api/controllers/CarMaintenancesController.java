package com.turkcell.rentACarProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.turkcell.rentACarProject.business.abstracts.CarMaintenanceService;
import com.turkcell.rentACarProject.business.dtos.dto.CarMaintenanceDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CarMaintenanceListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCarMaintenanceRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCarMaintenanceRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCarMaintenanceRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;


@RestController
@RequestMapping("/api/carmaintenances")
public class CarMaintenancesController {
    
	private CarMaintenanceService carMaintenanceService;
	
	@Autowired
	public CarMaintenancesController(CarMaintenanceService carMaintenanceService) {
		
		this.carMaintenanceService = carMaintenanceService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<CarMaintenanceListDto>> getAll() throws BusinessException{
		
		return this.carMaintenanceService.getAll();
	}
	
	@GetMapping("/getByCarId")
	public DataResult<List<CarMaintenanceListDto>> getByCarId(@RequestParam int id)throws BusinessException{
		
		return this.carMaintenanceService.getByCarId(id);
	}
	
	@GetMapping("/getByCarMaintenanceId")
	DataResult<CarMaintenanceDto> getByCarMaintenanceId (int id) throws BusinessException{
		
		return this.carMaintenanceService.getById(id);
	}
	
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException{
		
		return this.carMaintenanceService.add(createCarMaintenanceRequest);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateCarMaintenanceRequest updateCarMaintenanceRequest)throws BusinessException {
		
		return this.carMaintenanceService.update(updateCarMaintenanceRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws BusinessException{
		
		return this.carMaintenanceService.delete(deleteCarMaintenanceRequest);
	}
}
