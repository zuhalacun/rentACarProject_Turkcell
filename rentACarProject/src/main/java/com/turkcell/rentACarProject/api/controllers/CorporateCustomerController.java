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

import com.turkcell.rentACarProject.business.abstracts.CorporateCustomerService;
import com.turkcell.rentACarProject.business.dtos.dto.CorporateCustomerDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CorporateCustomerListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCorporateCustomerRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCorporateCustomerRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCorporateCustomerRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

@RestController
@RequestMapping("/api/corporate_customers")
public class CorporateCustomerController {

	private CorporateCustomerService corporateCustomerService;

	@Autowired
	public CorporateCustomerController(CorporateCustomerService corporateCustomerService) {
		this.corporateCustomerService = corporateCustomerService;
	}
	
	@GetMapping("/getAll")
	DataResult<List<CorporateCustomerListDto>> getAll() throws BusinessException{
		
		return this.corporateCustomerService.getAll();
	}
	
	@GetMapping("/getByCustomerId/{id}")
	DataResult<CorporateCustomerDto> getByCustomerId(@RequestParam("customerId") int id) throws BusinessException{
		
		return this.corporateCustomerService.getByCustomerId(id);
	}
	
	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException{
		
		return this.corporateCustomerService.add(createCorporateCustomerRequest);
	}
	

	@PutMapping("/update")
	Result update(@RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws BusinessException{
					
		return this.corporateCustomerService.update(updateCorporateCustomerRequest);
	}

	@DeleteMapping("/delete")
	Result delete(@RequestBody @Valid DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException{
		
		return this.corporateCustomerService.delete(deleteCorporateCustomerRequest);
	}


}
