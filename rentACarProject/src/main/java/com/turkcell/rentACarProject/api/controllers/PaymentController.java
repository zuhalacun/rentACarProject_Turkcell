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

import com.turkcell.rentACarProject.business.abstracts.PaymentService;
import com.turkcell.rentACarProject.business.dtos.dto.BrandDto;
import com.turkcell.rentACarProject.business.dtos.dto.PaymentDto;
import com.turkcell.rentACarProject.business.dtos.listdto.BrandListDto;
import com.turkcell.rentACarProject.business.dtos.listdto.PaymentListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateBrandRequest;
import com.turkcell.rentACarProject.business.requests.creates.CreatePaymentRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteBrandRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeletePaymentRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateBrandRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdatePaymentRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	private PaymentService paymentService;

	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<PaymentListDto>> getAll() throws BusinessException {
		
		return this.paymentService.getAll();
	}

	@GetMapping("/getByRentalId/{rentalId}")
	
	public DataResult<List<PaymentListDto>> getByRentalId(@RequestParam("rentalId") int rentalId) throws BusinessException {
		
		return this.paymentService.getAllPaymentByCarRental_RentalId(rentalId);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreatePaymentRequest createPaymentRequest) throws BusinessException {

		return this.paymentService.add(createPaymentRequest);
	}


	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdatePaymentRequest updatePaymentRequest) throws BusinessException {
		
		return this.paymentService.update(updatePaymentRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeletePaymentRequest deletePaymentRequest) throws BusinessException {
		
		return this.paymentService.delete(deletePaymentRequest);
	}

}
