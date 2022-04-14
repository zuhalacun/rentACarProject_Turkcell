package com.turkcell.rentACarProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentACarProject.business.abstracts.CustomerService;
import com.turkcell.rentACarProject.business.dtos.dto.CustomerDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CustomerListDto;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {

	private CustomerService customerService;

	@Autowired
	public CustomersController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping("/getAll")
	DataResult<List<CustomerListDto>> getAll() throws BusinessException{
		
		return this.customerService.getAll();
	}

	@GetMapping("getByCustomerId/{customerId}")
	DataResult<CustomerDto> getByCustomerId(@RequestParam("CustomerId") int id) throws BusinessException{
		
		return this.customerService.getByCustomerId(id);
	}
	

}
