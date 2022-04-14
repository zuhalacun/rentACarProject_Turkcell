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

import com.turkcell.rentACarProject.business.abstracts.IndividualCustomerService;
import com.turkcell.rentACarProject.business.dtos.dto.IndividualCustomerDto;
import com.turkcell.rentACarProject.business.dtos.listdto.IndividualCustomerListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateIndividualCustomerRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteIndividualCustomerRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateIndividualCustomerRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

@RestController
@RequestMapping("/api/individual_customers")
public class IndividualCustomerController {

	private IndividualCustomerService individualCustomerService;

	@Autowired
	public IndividualCustomerController(IndividualCustomerService individualCustomerService) {
		this.individualCustomerService = individualCustomerService;
	}
	
	@GetMapping("/getAll")
	DataResult<List<IndividualCustomerListDto>> getAll() throws BusinessException{
		
		return this.individualCustomerService.getAll();
	}
	
	@GetMapping("/getByCustomerId/{id}")
	DataResult<IndividualCustomerDto> getByCustomerId(@RequestParam("CustomerId") int id) throws BusinessException{
		
		return this.individualCustomerService.getByCustomerId(id);
	}

	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest) throws BusinessException{
		
		return this.individualCustomerService.add(createIndividualCustomerRequest);
	}

	@PutMapping("/update")
	Result update(@RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws BusinessException{
		
		return this.individualCustomerService.update(updateIndividualCustomerRequest);
	}

	@DeleteMapping("/delete")
	Result delete(@RequestBody @Valid DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws BusinessException{
		
		return this.individualCustomerService.delete(deleteIndividualCustomerRequest);
	}

}
