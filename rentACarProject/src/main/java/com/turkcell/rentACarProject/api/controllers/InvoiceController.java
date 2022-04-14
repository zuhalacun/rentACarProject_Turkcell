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

import com.turkcell.rentACarProject.business.abstracts.InvoiceService;
import com.turkcell.rentACarProject.business.dtos.dto.InvoiceDto;
import com.turkcell.rentACarProject.business.dtos.listdto.InvoiceListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateInvoiceRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteInvoiceRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateInvoiceRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

	private InvoiceService invoiceService;

	@Autowired
	public InvoiceController(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}
	
	@GetMapping("/getAllSorted/{orderOfSort}")
	public DataResult<List<InvoiceListDto>> getAllSorted(@RequestParam("orderOfSort") String orderOfSort) throws BusinessException {

		return this.invoiceService.getAllSorted(orderOfSort);
	}
	

	@GetMapping("/getByCustomerId/{customerId}")
	DataResult<List<InvoiceListDto>> getByCustomerId(@RequestParam("customerId") int id){
		
		return this.invoiceService.getByCustomerId(id);
	}

	@GetMapping("/getById/{invoiceId}")
	DataResult<InvoiceDto> getById(@RequestParam("invoiceId") int id) throws BusinessException{
		
		return this.invoiceService.getById(id);
	}

	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateInvoiceRequest createInvoiceRequest) throws BusinessException{
		
		return this.invoiceService.add(createInvoiceRequest);
	}
	
	@PutMapping("/update")
	Result update(@RequestBody @Valid UpdateInvoiceRequest updateInvoiceRequest) throws BusinessException{
		
		return this.invoiceService.update(updateInvoiceRequest);
	}

	@DeleteMapping("/delete")
	Result delete(@RequestBody @Valid DeleteInvoiceRequest deleteInvoiceRequest) throws BusinessException{
		
		return this.invoiceService.delete(deleteInvoiceRequest);
	}
}
