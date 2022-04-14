package com.turkcell.rentACarProject.business.abstracts;


import java.util.List;

import com.turkcell.rentACarProject.business.dtos.dto.CarMaintenanceDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CarMaintenanceListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCarMaintenanceRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCarMaintenanceRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCarMaintenanceRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

public interface CarMaintenanceService {
	
	DataResult<List<CarMaintenanceListDto>> getAll() throws BusinessException;
	DataResult<List<CarMaintenanceListDto>> getByCarId(int id) throws BusinessException;
	DataResult<CarMaintenanceDto> getById(int id) throws BusinessException;

	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException;
    Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException;
    Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws BusinessException;
}

