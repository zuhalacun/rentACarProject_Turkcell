package com.turkcell.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACarProject.business.abstracts.CityService;
import com.turkcell.rentACarProject.business.constants.Messages;
import com.turkcell.rentACarProject.business.dtos.dto.CityDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CityListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCityRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCityRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCityRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.SuccessDataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;
import com.turkcell.rentACarProject.core.utilities.results.result.SuccessResult;
import com.turkcell.rentACarProject.dataAccess.abstracts.CityDao;
import com.turkcell.rentACarProject.entities.concretes.City;
import com.turkcell.rentACarProject.entities.concretes.Color;

@Service
public class CityManager implements CityService{

	private CityDao cityDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
	    this.cityDao = cityDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<CityListDto>> getAll() throws BusinessException {
		
		List<City> result = this.cityDao.findAll();

        List<CityListDto> response = result.stream()
                .map(city -> this.modelMapperService.forDto().map(city, CityListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult(response);
	}

	@Override
	public DataResult<CityDto> getById(int cityId) throws BusinessException {
		
		checkIfCityIdExists(cityId);

        City city = this.cityDao.getById(cityId);
        CityDto cityDto = this.modelMapperService.forDto().map(city, CityDto.class);

        return new SuccessDataResult(cityDto);
	}

	@Override
	public Result add(CreateCityRequest createCityRequest) throws BusinessException {

		checkIfCityExistsByName(createCityRequest.getCityName());

		City city = this.modelMapperService.forRequest().map(createCityRequest,City.class);

        this.cityDao.save(city);

        return new SuccessResult(Messages.cityAdded);
	}

	@Override
	public Result update(UpdateCityRequest updateCityRequest) throws BusinessException {
		
		checkIfCityIdExists(updateCityRequest.getCityId());
	       

        City city = this.modelMapperService.forRequest().map(updateCityRequest,City.class);

        this.cityDao.save(city);

        return new SuccessResult(Messages.cityUpdated);
	}

	@Override
	public Result delete(DeleteCityRequest deleteCityRequest) throws BusinessException {

		checkIfCityIdExists(deleteCityRequest.getCityId());

        City city = this.modelMapperService.forRequest().map(deleteCityRequest,City.class);

        this.cityDao.delete(city);

        return new SuccessResult(Messages.cityDeleted);
	}

	private void checkIfCityIdExists(int id) throws BusinessException {

        if(!this.cityDao.existsById(id)){
            throw new BusinessException(Messages.cityIdExists);
        }
	}
	
	public void checkIfCityExistsByName(String name) throws BusinessException {
       
		if(this.cityDao.getByName(name)!=null)
            throw new BusinessException(Messages.cityNameExists);
    }
}
