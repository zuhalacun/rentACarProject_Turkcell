package com.turkcell.rentACarProject.business.abstracts;

import java.util.List;

import com.turkcell.rentACarProject.business.dtos.dto.CityDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CityListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCityRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCityRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCityRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

public interface CityService {

	DataResult<List<CityListDto>> getAll() throws BusinessException;
	DataResult<CityDto> getById(int cityId) throws BusinessException;

	Result add(CreateCityRequest createCityRequest) throws BusinessException;
	Result update(UpdateCityRequest updateCityRequest) throws BusinessException;
	Result delete(DeleteCityRequest deleteCityRequest) throws BusinessException;
	
}
