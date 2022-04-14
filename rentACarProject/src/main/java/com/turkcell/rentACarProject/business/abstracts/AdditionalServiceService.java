package com.turkcell.rentACarProject.business.abstracts;

import java.util.List;

import com.turkcell.rentACarProject.business.dtos.dto.AdditionalServiceDto;
import com.turkcell.rentACarProject.business.dtos.listdto.AdditionalServiceListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateAdditionalServiceRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteAdditionalServiceRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateAdditionalServiceRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

public interface AdditionalServiceService {

	DataResult<List<AdditionalServiceListDto>> getAll() throws BusinessException;
	DataResult<AdditionalServiceDto> getByAdditionalServiceId(int id) throws BusinessException;
	
	
	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException;
	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException;
	Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException;
}
