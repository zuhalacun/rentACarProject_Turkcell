package com.turkcell.rentACarProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentACarProject.business.abstracts.CityService;
import com.turkcell.rentACarProject.business.dtos.dto.CityDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CityListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCityRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCityRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCityRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {

	private CityService cityService;

	public CitiesController(CityService cityService) {
		super();
		this.cityService = cityService;
	}
	
	@GetMapping("/getAll")
	DataResult<List<CityListDto>> getAll() throws BusinessException{
		
		return this.cityService.getAll();
	}
	
	@GetMapping("/getById/{cityId}")
	DataResult<CityDto> getById(@RequestParam("cityId") Integer id) throws BusinessException{
		
		return this.cityService.getById(id);
	}
	
	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateCityRequest createCityRequest) throws BusinessException{
		
		return this.cityService.add(createCityRequest);
	}

	@PutMapping("/update")
	Result update(@RequestBody @Valid UpdateCityRequest updateCityRequest) throws BusinessException{
		
		return this.cityService.update(updateCityRequest);
	}

	@DeleteMapping("/delete")
	Result delete(@RequestBody @Valid DeleteCityRequest deleteCityRequest) throws BusinessException{
		
		return this.cityService.delete(deleteCityRequest);
	}


}
