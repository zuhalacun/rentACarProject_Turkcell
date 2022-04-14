package com.turkcell.rentACarProject.business.abstracts;

import java.util.List;

import com.turkcell.rentACarProject.business.dtos.dto.ColorDto;
import com.turkcell.rentACarProject.business.dtos.listdto.ColorListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateColorRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteColorRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateColorRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

public interface ColorService {

	DataResult<List<ColorListDto>> getAll() throws BusinessException;
    DataResult<ColorDto> getById(int colorId) throws BusinessException;
	
    Result add(CreateColorRequest createColorRequest) throws BusinessException;
    Result update(UpdateColorRequest updateColorRequest) throws BusinessException;
    Result delete(DeleteColorRequest deleteColorRequest) throws BusinessException;
	
}


