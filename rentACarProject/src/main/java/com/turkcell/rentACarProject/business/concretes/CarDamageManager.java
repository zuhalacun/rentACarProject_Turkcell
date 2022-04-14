package com.turkcell.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.turkcell.rentACarProject.business.abstracts.CarDamageService;
import com.turkcell.rentACarProject.business.abstracts.CarService;
import com.turkcell.rentACarProject.business.constants.Messages;
import com.turkcell.rentACarProject.business.dtos.listdto.CarDamageListDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CarMaintenanceListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCarDamageRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCarDamageRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCarDamageRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.SuccessDataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;
import com.turkcell.rentACarProject.core.utilities.results.result.SuccessResult;
import com.turkcell.rentACarProject.dataAccess.abstracts.CarDamageDao;
import com.turkcell.rentACarProject.entities.concretes.CarDamage;

@Service
public class CarDamageManager implements CarDamageService{

	private CarDamageDao carDamageDao;
    private ModelMapperService modelMapperService;
    private CarService carService;

    public CarDamageManager(CarDamageDao carDamageDao, ModelMapperService modelMapperService,CarService carService) {
        this.carDamageDao = carDamageDao;
        this.modelMapperService = modelMapperService;
        this.carService=carService;
    }
	
	@Override
	public DataResult<List<CarDamageListDto>> getAll() throws BusinessException {
		
		List<CarDamage> result = this.carDamageDao.findAll();

        List<CarDamageListDto> response = result.stream().map(carDamage->this.modelMapperService.forDto().map(carDamage,CarDamageListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<CarDamageListDto>>(response);
	}

	@Override
	public DataResult<List<CarDamageListDto>> getByCarId(int id) throws BusinessException {
		
		List<CarDamage> result = this.carDamageDao.getByCarId(id);
		List<CarDamageListDto> response = result.stream().map(
				carDamage -> this.modelMapperService.forDto().map(carDamage, CarDamageListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarDamageListDto>>(response);
	}

	@Override
	public Result add(CreateCarDamageRequest createCarDamageRequest) throws BusinessException {
		
		checkIfCarIsExists(createCarDamageRequest.getCarId());
		
		CarDamage carDamage = this.modelMapperService.forRequest().map(createCarDamageRequest,CarDamage.class);

        this.carDamageDao.save(carDamage);

        return new SuccessResult(Messages.carDamageAdded);
	}

	@Override
	public Result update(UpdateCarDamageRequest updateCarDamageRequest) throws BusinessException {
		
		checkIfCarDamageExistsById(updateCarDamageRequest.getId());

        CarDamage carDamage = this.modelMapperService.forRequest().map(updateCarDamageRequest,CarDamage.class);

        this.carDamageDao.save(carDamage);

        return new SuccessResult(Messages.carDamageUpdated);
	}

	@Override
	public Result delete(DeleteCarDamageRequest deleteCarDamageRequest) throws BusinessException {
		
		 checkIfCarDamageExistsById(deleteCarDamageRequest.getId());

	        CarDamage carDamage = this.modelMapperService.forRequest().map(deleteCarDamageRequest,CarDamage.class);

	        this.carDamageDao.delete(carDamage);

	        return new SuccessResult(Messages.carDamageDeleted);
	}

	 private void checkIfCarDamageExistsById(int id)throws BusinessException{
	        if(!this.carDamageDao.existsById(id))
	            throw new BusinessException(Messages.carDamageIdExists);
	    }
	 
	 private void checkIfCarIsExists(int id) throws BusinessException {
			
			if (!carService.getByCarId(id).isSuccess()) {
				throw new BusinessException(Messages.carIdExists);
			}
		}
}
