package com.turkcell.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACarProject.business.abstracts.BrandService;
import com.turkcell.rentACarProject.business.constants.Messages;
import com.turkcell.rentACarProject.business.dtos.dto.BrandDto;
import com.turkcell.rentACarProject.business.dtos.listdto.BrandListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateBrandRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteBrandRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateBrandRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.SuccessDataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.ErrorResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;
import com.turkcell.rentACarProject.core.utilities.results.result.SuccessResult;
import com.turkcell.rentACarProject.dataAccess.abstracts.BrandDao;
import com.turkcell.rentACarProject.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService{
	
	private BrandDao brandDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<BrandListDto>> getAll() throws BusinessException {
		
		List<Brand> result=this.brandDao.findAll();

        List<BrandListDto> response = result.stream().map(brand->this.modelMapperService.forDto().map(brand,BrandListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<BrandListDto>>(response);
	}

	@Override
	public DataResult<BrandDto> getByBrandId(int id) throws BusinessException {

		checkIfBrandExistsById(id);

        Brand brand = this.brandDao.getById(id);

        BrandDto response = this.modelMapperService.forDto().map(brand,BrandDto.class);

        return new SuccessDataResult<BrandDto>(response);
    }


	@Override
	public Result add(CreateBrandRequest createBrandRequest) throws BusinessException {
		 
		   checkIfBrandExistsByName(createBrandRequest.getBrandName());

	        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest,Brand.class);

	        this.brandDao.save(brand);

	        return new SuccessResult(Messages.brandAdded);
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException {
		
		    checkIfBrandExistsById(updateBrandRequest.getBrandId());
	      
	        Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest,Brand.class);

	        this.brandDao.save(brand);

	        return new SuccessResult(Messages.brandUpdated);
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException {
		
		checkIfBrandExistsById(deleteBrandRequest.getBrandId());

        Brand brand = this.modelMapperService.forRequest().map(deleteBrandRequest,Brand.class);

        this.brandDao.delete(brand);

        return new SuccessResult(Messages.brandDeleted);
	}

	private void checkIfBrandExistsByName(String name) throws BusinessException {
        if(this.brandDao.getByBrandName(name) != null)
            throw new BusinessException(Messages.brandNameExists);
    }

    private void checkIfBrandExistsById(int id) throws BusinessException {
        if(!this.brandDao.existsById(id))
            throw new BusinessException(Messages.brandIdExists);
    }
	
}
