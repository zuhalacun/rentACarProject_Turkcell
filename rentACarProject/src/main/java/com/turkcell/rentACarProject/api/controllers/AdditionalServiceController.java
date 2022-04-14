package com.turkcell.rentACarProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentACarProject.business.abstracts.AdditionalServiceService;
import com.turkcell.rentACarProject.business.dtos.dto.AdditionalServiceDto;
import com.turkcell.rentACarProject.business.dtos.listdto.AdditionalServiceListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateAdditionalServiceRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteAdditionalServiceRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateAdditionalServiceRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/additionals")
public class AdditionalServiceController {

	private AdditionalServiceService additionalServiceService;

	@Autowired
	public AdditionalServiceController(AdditionalServiceService additionalServiceService) {
		this.additionalServiceService = additionalServiceService;
	}
	
	@GetMapping("/getAll")
	DataResult<List<AdditionalServiceListDto>> getAll() throws BusinessException{
		
		return this.additionalServiceService.getAll();
	}


	@GetMapping("/getByAdditionalServiceId/{additionalServiceId}")
	DataResult<AdditionalServiceDto> getByAdditionalServiceId(@RequestParam("additionalServiceId") Integer id) throws BusinessException{
		
		return this.additionalServiceService.getByAdditionalServiceId(id);
	}
	
	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException{
		
		return this.additionalServiceService.add(createAdditionalServiceRequest);
	}
	
	@PutMapping("/update")
	Result update(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException{
		
		return this.additionalServiceService.update(updateAdditionalServiceRequest);
	}

	@DeleteMapping("/delete")
	Result delete(@RequestBody @Valid DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException{
		
		return this.additionalServiceService.delete(deleteAdditionalServiceRequest);
	}
}
