package com.turkcell.rentACarProject.business.abstracts;

import java.util.List;

import com.turkcell.rentACarProject.business.dtos.dto.CorporateCustomerDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CorporateCustomerListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCorporateCustomerRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCorporateCustomerRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCorporateCustomerRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

public interface CorporateCustomerService {

	DataResult<List<CorporateCustomerListDto>> getAll() throws BusinessException;
	DataResult<CorporateCustomerDto> getByCustomerId(int id) throws BusinessException;

	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException;
	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws BusinessException;
	Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException;
	
}
