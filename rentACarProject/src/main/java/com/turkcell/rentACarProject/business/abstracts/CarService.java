package com.turkcell.rentACarProject.business.abstracts;

import java.util.List;

import com.turkcell.rentACarProject.business.dtos.dto.CarDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CarListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCarRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCarRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCarRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

public interface CarService {
	
	DataResult<CarDto> getByCarId(int carId) throws BusinessException;
	DataResult<List<CarListDto>> getAllPaged(int pageNo, int pageSize);
	DataResult<List<CarListDto>> getAllSorted(String direction) throws BusinessException;
	
	Result add(CreateCarRequest createCarRequest) throws BusinessException;
	Result update(UpdateCarRequest updateCarRequest) throws BusinessException;
    Result delete(DeleteCarRequest deleteCarRequest) throws BusinessException;

	Result updateCarKilometer(int carId, int kilometer) throws BusinessException;
	Result updateCarCity(int carId, int cityId) throws BusinessException;
}

