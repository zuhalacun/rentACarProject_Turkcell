package com.turkcell.rentACarProject.business.abstracts;

import java.util.List;

import com.turkcell.rentACarProject.business.dtos.listdto.CarDamageListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCarDamageRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCarDamageRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCarDamageRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

public interface CarDamageService {

	DataResult<List<CarDamageListDto>> getAll() throws BusinessException;
	DataResult<List<CarDamageListDto>> getByCarId(int id) throws BusinessException;
	
    Result add(CreateCarDamageRequest createCarDamageRequest) throws BusinessException;
    Result update(UpdateCarDamageRequest updateCarDamageRequest) throws BusinessException;
    Result delete(DeleteCarDamageRequest deleteCarDamageRequest) throws BusinessException;
}
