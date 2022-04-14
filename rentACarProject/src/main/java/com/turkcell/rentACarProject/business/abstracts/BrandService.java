package com.turkcell.rentACarProject.business.abstracts;

import java.util.List;

import com.turkcell.rentACarProject.business.dtos.dto.BrandDto;
import com.turkcell.rentACarProject.business.dtos.listdto.BrandListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateBrandRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteBrandRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateBrandRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

public interface BrandService {

	DataResult<List<BrandListDto>> getAll() throws BusinessException;
	DataResult<BrandDto> getByBrandId(int id) throws BusinessException;
	
	Result add(CreateBrandRequest createBrandRequest) throws BusinessException;
	Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException;
	Result delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException;

}
