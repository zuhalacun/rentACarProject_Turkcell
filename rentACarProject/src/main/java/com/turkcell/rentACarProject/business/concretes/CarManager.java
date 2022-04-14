package com.turkcell.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.turkcell.rentACarProject.business.abstracts.BrandService;
import com.turkcell.rentACarProject.business.abstracts.CarService;
import com.turkcell.rentACarProject.business.abstracts.CityService;
import com.turkcell.rentACarProject.business.abstracts.ColorService;
import com.turkcell.rentACarProject.business.constants.Messages;
import com.turkcell.rentACarProject.business.dtos.dto.CarDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CarListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCarRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCarRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCarRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.SuccessDataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;
import com.turkcell.rentACarProject.core.utilities.results.result.SuccessResult;
import com.turkcell.rentACarProject.dataAccess.abstracts.CarDao;
import com.turkcell.rentACarProject.entities.concretes.Car;
import com.turkcell.rentACarProject.entities.concretes.City;

@Service
public class CarManager implements CarService{

    private CarDao carDao;
    private ModelMapperService modelMapperService;
    private CityService cityService;
    private ColorService colorService;
    private BrandService brandService;
    
    @Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService,CityService cityService,
			ColorService colorService,BrandService brandService) {
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
		this.cityService=cityService;
		this.colorService=colorService;
		this.brandService=brandService;
	}

	@Override
	public DataResult<CarDto> getByCarId(int carId) throws BusinessException {
		
		checkIfCarExistsById(carId);

        Car car = this.carDao.getById(carId);

        CarDto carDto = this.modelMapperService.forDto().map(car,CarDto.class);

        return new SuccessDataResult<CarDto>(carDto);
	}

	@Override
	public DataResult<List<CarListDto>> getAllPaged(int pageNo, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo,pageSize);

        List<Car> result = this.carDao.findAll();

        List<CarListDto> response = result.stream().map(car->this.modelMapperService.forDto().map(car,CarListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<CarListDto>>(response);
	}

	@Override
	public DataResult<List<CarListDto>> getAllSorted(String direction) throws BusinessException {
		
		Sort sort = Sort.by(direction,"carDailyPrice");

        List<Car> result =this.carDao.findAll(sort);

        List<CarListDto> response = result.stream().map(car->this.modelMapperService.forDto().map(car,CarListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<CarListDto>>(response);
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) throws BusinessException {
		
		checkIfColorExist(createCarRequest.getColorId());
		checkIfBrandExists(createCarRequest.getBrandId());
		
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);

		return new SuccessResult(Messages.carAdded);
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) throws BusinessException {
		
		checkIfCarExistsById(updateCarRequest.getCarId());

        Car car = this.modelMapperService.forRequest().map(updateCarRequest,Car.class);

        this.carDao.save(car);

        return new SuccessResult(Messages.carUpdated);
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) throws BusinessException {
		
		checkIfCarExistsById(deleteCarRequest.getCarId());

        Car car = this.modelMapperService.forRequest().map(deleteCarRequest,Car.class);

        this.carDao.delete(car);

        return new SuccessResult(Messages.carDeleted);
	}

	@Override
	public Result updateCarKilometer(int carId, int kilometer) throws BusinessException{
		
		Car car = this.carDao.getById(carId);
		car.setCarKilometer(kilometer);
		this.carDao.save(car);
		return new SuccessResult(Messages.carUptadedKilometer);
	}

	@Override
	public Result updateCarCity(int carId, int cityId) throws BusinessException  {
		
		Car car = this.carDao.getById(carId);
		City city = modelMapperService.forRequest().map(cityService.getById(cityId), City.class);
		car.setCity(city);   
		this.carDao.save(car);
		return new SuccessResult(Messages.carUptadedCity);
	}

	 private void checkIfCarExistsById(int id) throws BusinessException {
	        if(!this.carDao.existsById(id))
	            throw new BusinessException(Messages.carIdExists);
	    }
	 
	 private void checkIfColorExist(int colorId) throws BusinessException{
		 if(!colorService.getById(colorId).isSuccess())
			 throw new BusinessException(Messages.colorIdExists);
	 }
	
	 private void checkIfBrandExists(int brandId) throws BusinessException{
		 if(!brandService.getByBrandId(brandId).isSuccess())
			 throw new BusinessException(Messages.brandIdExists);
	 }
  }
