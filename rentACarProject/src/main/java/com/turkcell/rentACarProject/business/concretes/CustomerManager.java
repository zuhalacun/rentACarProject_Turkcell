package com.turkcell.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACarProject.business.abstracts.CustomerService;
import com.turkcell.rentACarProject.business.constants.Messages;
import com.turkcell.rentACarProject.business.dtos.dto.CustomerDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CustomerListDto;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.SuccessDataResult;
import com.turkcell.rentACarProject.dataAccess.abstracts.CustomerDao;
import com.turkcell.rentACarProject.entities.concretes.Customer;

@Service
public class CustomerManager implements CustomerService{

	private CustomerDao customerDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CustomerManager(CustomerDao customerDao, ModelMapperService modelMapperService) {
	
		this.customerDao = customerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<CustomerListDto>> getAll() throws BusinessException {
		
        List<Customer> result = this.customerDao.findAll();
		
		List<CustomerListDto> response = result.stream().map(customer -> this.modelMapperService.forDto().map(customer, CustomerListDto.class
				)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CustomerListDto>>(response);

	}

	@Override
	public DataResult<CustomerDto> getByCustomerId(int id) throws BusinessException {

		checkIfCustomerIdExists(id);
		
		Customer customer = this.customerDao.getById(id);
		
		CustomerDto response = this.modelMapperService.forDto().map(customer, CustomerDto.class);
		
		return new SuccessDataResult<CustomerDto>(response);
	}

   public void checkIfCustomerIdExists(int id) throws BusinessException {
		
		if(!this.customerDao.existsById(id)) {
			
			throw new BusinessException(Messages.customerIdExists);
		}
	}


}
