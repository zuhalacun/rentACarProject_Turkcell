package com.turkcell.rentACarProject.business.abstracts;

import java.util.List;

import com.turkcell.rentACarProject.business.dtos.dto.CustomerDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CustomerListDto;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

public interface CustomerService {

	DataResult<List<CustomerListDto>> getAll() throws BusinessException;
	DataResult<CustomerDto> getByCustomerId(int id) throws BusinessException;
}
