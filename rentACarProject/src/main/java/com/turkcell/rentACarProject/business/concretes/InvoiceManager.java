package com.turkcell.rentACarProject.business.concretes;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.turkcell.rentACarProject.business.abstracts.CarRentalService;
import com.turkcell.rentACarProject.business.abstracts.InvoiceService;
import com.turkcell.rentACarProject.business.constants.Messages;
import com.turkcell.rentACarProject.business.dtos.dto.InvoiceDto;
import com.turkcell.rentACarProject.business.dtos.listdto.InvoiceListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateInvoiceRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteInvoiceRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateInvoiceRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.SuccessDataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.ErrorResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;
import com.turkcell.rentACarProject.core.utilities.results.result.SuccessResult;
import com.turkcell.rentACarProject.dataAccess.abstracts.InvoiceDao;
import com.turkcell.rentACarProject.entities.concretes.Invoice;

@Service
public class InvoiceManager implements InvoiceService{

	private InvoiceDao invoiceDao;
	private ModelMapperService modelMapperService;
	private CarRentalService carRentalService;

	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService,
			CarRentalService carRentalService) {
		super();
		this.invoiceDao = invoiceDao;
		this.modelMapperService = modelMapperService;
		this.carRentalService = carRentalService;
	}

	@Override
	public DataResult<List<InvoiceListDto>> getAllSorted(String direction) throws BusinessException {
        
        Sort sort = Sort.by(direction,"date");

        List<Invoice> result=this.invoiceDao.findAll(sort);

        List<InvoiceListDto> response = result.stream().map(invoice->this.modelMapperService.forDto().map(invoice,InvoiceListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<InvoiceListDto>>(response);
	}

	@Override
	public DataResult<InvoiceDto> getById(int id) throws BusinessException {
		
		    checkIfInvoiceExistsById(id);

	        Invoice invoice = this.invoiceDao.getById(id);

	        InvoiceDto invoiceDto = this.modelMapperService.forDto().map(invoice,InvoiceDto.class);

	        return new SuccessDataResult<InvoiceDto>(invoiceDto);
	}

	@Override
	public DataResult<List<InvoiceListDto>> getByCustomerId(int id) {
		
		List<Invoice> invoices = this.invoiceDao.getByCustomerId(id);
     
        List<InvoiceListDto> response = invoices.stream().map(invoice->this.modelMapperService.forDto().map(invoice,InvoiceListDto.class)).collect(Collectors.toList());
      
        return new SuccessDataResult<List<InvoiceListDto>>(response);
	}

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) throws BusinessException {
		
		checkIfInvoiceAlreadyExists(createInvoiceRequest.getRentalId());
	    checkIfPaymentIsMade(createInvoiceRequest.getRentalId());
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.invoiceAdded);
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) throws BusinessException {
		
		checkIfInvoiceExistsById(updateInvoiceRequest.getInvoiceId());
		Invoice invoice = this.modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.invoiceUpdated);
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) throws BusinessException {
		
		checkIfInvoiceExistsById(deleteInvoiceRequest.getInvoiceId());

        Invoice invoice = this.modelMapperService.forRequest().map(deleteInvoiceRequest,Invoice.class);

        this.invoiceDao.delete(invoice);

        return new SuccessResult(Messages.invoiceDeleted);
	}

	
	private void checkIfInvoiceExistsById(int id) throws BusinessException {
        if(!this.invoiceDao.existsById(id))
            throw new BusinessException(Messages.invoiceIdExists);
    }
	
	private void checkIfInvoiceAlreadyExists(int rentalId) throws BusinessException {
        if(this.invoiceDao.getById(rentalId)!=null)
            throw new BusinessException(Messages.invoiceRentalIdExists);
    }

	private Result checkIfPaymentIsMade(int rentalId) throws BusinessException {
		if (carRentalService.getByRentalId(rentalId) != null) {
			return new SuccessResult();
		} else
			return new ErrorResult(Messages.paymentIdExists);
	}
}
