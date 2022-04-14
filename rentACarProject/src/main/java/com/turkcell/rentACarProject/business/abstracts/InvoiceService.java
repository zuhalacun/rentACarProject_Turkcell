package com.turkcell.rentACarProject.business.abstracts;

import java.util.List;

import com.turkcell.rentACarProject.business.dtos.dto.InvoiceDto;
import com.turkcell.rentACarProject.business.dtos.listdto.InvoiceListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateInvoiceRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteInvoiceRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateInvoiceRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;
import com.turkcell.rentACarProject.entities.concretes.Invoice;

public interface InvoiceService {

	DataResult<List<InvoiceListDto>> getAllSorted(String direction) throws BusinessException;
	DataResult<InvoiceDto> getById(int id) throws BusinessException;
	DataResult<List<InvoiceListDto>> getByCustomerId(int id);
	
	Result add(CreateInvoiceRequest createInvoiceRequest) throws BusinessException;
	Result update(UpdateInvoiceRequest updateInvoiceRequest) throws BusinessException;
	Result delete(DeleteInvoiceRequest deleteInvoiceRequest) throws BusinessException;
	
     
}
