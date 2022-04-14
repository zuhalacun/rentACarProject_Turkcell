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

import com.turkcell.rentACarProject.business.abstracts.OrderedAdditionalServiceService;
import com.turkcell.rentACarProject.business.dtos.dto.AdditionalServiceDto;
import com.turkcell.rentACarProject.business.dtos.listdto.OrderedAdditionalServiceListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteAdditionalServiceRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteOrderedAdditionalServiceRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateOrderedAdditionalServiceRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

@RestController
@RequestMapping("/api/orderedadditionals")
public class OrderedServiceController {

	private OrderedAdditionalServiceService orderedAdditionalServiceService;

	@Autowired
	public OrderedServiceController(OrderedAdditionalServiceService orderedAdditionalServiceService) {
		this.orderedAdditionalServiceService = orderedAdditionalServiceService;
	}
	
	@GetMapping("/getAllByRentalId/{rentalId}")
	DataResult<List<OrderedAdditionalServiceListDto>> findAllByRentalId(@RequestParam("rentalId") int rentalId) throws BusinessException {
		return orderedAdditionalServiceService.getByOrderedAdditional_RentalId(rentalId);
	}
	
	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest) throws BusinessException{
		
		return this.orderedAdditionalServiceService.add(createOrderedAdditionalServiceRequest);
	}
	
	@PutMapping("/update")
	Result update(@RequestBody @Valid UpdateOrderedAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException{
		
		return this.orderedAdditionalServiceService.update(updateAdditionalServiceRequest);
	}

	@DeleteMapping("/delete")
	Result delete(@RequestBody @Valid DeleteOrderedAdditionalServiceRequest deleteOrderedAdditionalServiceRequest) throws BusinessException{
		
		return this.orderedAdditionalServiceService.delete(deleteOrderedAdditionalServiceRequest);
	}
}
