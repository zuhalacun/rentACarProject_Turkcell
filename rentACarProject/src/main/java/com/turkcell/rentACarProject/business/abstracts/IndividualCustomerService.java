package com.turkcell.rentACarProject.business.abstracts;

import java.util.List;

import com.turkcell.rentACarProject.business.dtos.dto.IndividualCustomerDto;
import com.turkcell.rentACarProject.business.dtos.listdto.IndividualCustomerListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateIndividualCustomerRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteIndividualCustomerRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateIndividualCustomerRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

public interface IndividualCustomerService {

	DataResult<List<IndividualCustomerListDto>> getAll() throws BusinessException;
	DataResult<IndividualCustomerDto> getByCustomerId(int id) throws BusinessException;
	
	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws BusinessException;
	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws BusinessException;
	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws BusinessException;
	

}
