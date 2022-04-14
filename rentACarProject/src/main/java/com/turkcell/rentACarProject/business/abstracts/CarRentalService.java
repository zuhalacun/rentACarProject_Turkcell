package com.turkcell.rentACarProject.business.abstracts;

import java.util.List;

import com.turkcell.rentACarProject.business.dtos.dto.CarRentalDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CarRentalListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCarRentalRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCarRentalRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCarRentalRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateLateDeliveriesRentalCarRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

public interface CarRentalService {
	
	DataResult<List<CarRentalListDto>> getAll() throws BusinessException;
    List<CarRentalListDto> getByCarId(int id) throws BusinessException;
	DataResult<CarRentalDto> getByRentalId(int id) throws BusinessException;
	
	Result addForIndividualCustomer(CreateCarRentalRequest createCarRentalRequest) throws BusinessException;
	Result addForCorporateCustomer(CreateCarRentalRequest createCarRentalRequest) throws BusinessException;
	Result update(UpdateCarRentalRequest updateCarRentalRequest) throws BusinessException;
    Result delete(DeleteCarRentalRequest deleteCarRentalRequest) throws BusinessException;
    
    Result lateDeliveriesUpdate(int id, UpdateLateDeliveriesRentalCarRequest updateLateDeliveriesRentalCarRequest) throws BusinessException;

}
