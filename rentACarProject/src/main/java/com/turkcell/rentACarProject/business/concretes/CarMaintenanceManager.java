package com.turkcell.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.turkcell.rentACarProject.business.abstracts.CarMaintenanceService;
import com.turkcell.rentACarProject.business.abstracts.CarRentalService;
import com.turkcell.rentACarProject.business.abstracts.CarService;
import com.turkcell.rentACarProject.business.constants.Messages;
import com.turkcell.rentACarProject.business.dtos.dto.CarDto;
import com.turkcell.rentACarProject.business.dtos.dto.CarMaintenanceDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CarListDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CarMaintenanceListDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CarRentalListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCarMaintenanceRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCarMaintenanceRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCarMaintenanceRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.SuccessDataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;
import com.turkcell.rentACarProject.core.utilities.results.result.SuccessResult;
import com.turkcell.rentACarProject.dataAccess.abstracts.CarMaintenanceDao;
import com.turkcell.rentACarProject.entities.concretes.Brand;
import com.turkcell.rentACarProject.entities.concretes.CarMaintenance;

@Service
public class CarMaintenanceManager implements CarMaintenanceService{

	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	private CarRentalService carRentalService;
	private CarService carService;
	
	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao,
			ModelMapperService modelMapperService, @Lazy CarRentalService carRentalService,CarService carService) {
		
		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
		this.carRentalService = carRentalService;
		this.carService=carService;
	}

	@Override
	public DataResult<List<CarMaintenanceListDto>> getAll() throws BusinessException {
		 
		List<CarMaintenance> result=this.carMaintenanceDao.findAll();

	    List<CarMaintenanceListDto> response = result.stream().map(carMaintenance->this.modelMapperService.forDto().
	                map(carMaintenance,CarMaintenanceListDto.class)).collect(Collectors.toList());

	    return new SuccessDataResult<List<CarMaintenanceListDto>>(response);
	}

	@Override
	public DataResult<List<CarMaintenanceListDto>> getByCarId(int id) throws BusinessException {
		
		List<CarMaintenance> result = this.carMaintenanceDao.getByCarId(id);
		List<CarMaintenanceListDto> response = result.stream().map(
				carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarMaintenanceListDto>>(response);
	}

	@Override
	public DataResult<CarMaintenanceDto> getById(int id) throws BusinessException {
		
		checkIfCarMaintenanceExistsById(id);
		CarMaintenance carMaintenance = this.carMaintenanceDao.getById(id);
		CarMaintenanceDto response = this.modelMapperService.forDto().map(carMaintenance,
				CarMaintenanceDto.class);
		return new SuccessDataResult<CarMaintenanceDto>(response);
	}
	
	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException {
		
		checkIfCarIsExists(createCarMaintenanceRequest.getCarId());
		checkIfIsRented(createCarMaintenanceRequest);
		
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest,
				CarMaintenance.class);
		
		carMaintenanceDao.save(carMaintenance);
		return new SuccessResult(Messages.carMaintenanceAdded);
	}

	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException {
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest,
				CarMaintenance.class);
		carMaintenanceDao.save(carMaintenance);
		return new SuccessResult(Messages.carMaintenanceUpdated);
	}

	@Override
	public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws BusinessException {
		
		checkIfCarMaintenanceExistsById(deleteCarMaintenanceRequest.getMaintenanceId());

		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(deleteCarMaintenanceRequest,CarMaintenance.class);

        this.carMaintenanceDao.delete(carMaintenance);
		return new SuccessResult(Messages.carMaintenanceDeleted);
	}

	private void checkIfCarMaintenanceExistsById(int id) throws BusinessException {
		
		if(!this.carMaintenanceDao.existsById(id))
            throw new BusinessException(Messages.carMaintenanceIdExists);
    }
	
	private void checkIfCarIsExists(int id) throws BusinessException {
		
		if (!carService.getByCarId(id).isSuccess()) {
			throw new BusinessException(Messages.carIdExists);
		}
	}

	private void checkIfIsRented(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException { 
		 
		List<CarRentalListDto> result = this.carRentalService.getByCarId(createCarMaintenanceRequest.getCarId());

	        if (result != null) {
	            if (result.isEmpty()) {
	                throw new BusinessException(Messages.carRentalIdExists );
	            }

	            for (CarRentalListDto carRental : result) {
	                if (carRental.getRentalReturnDate() == null) {
	                    throw new BusinessException(Messages.carMaintenanceCannot);
	                }

	                if (  
	                        (createCarMaintenanceRequest.getReturnDate().isEqual(carRental.getRentalDate()) ||
	                        		createCarMaintenanceRequest.getReturnDate().isAfter(carRental.getRentalDate()))
	                         &&
	                        (createCarMaintenanceRequest.getReturnDate().isEqual(carRental.getRentalReturnDate())) ||
                                    createCarMaintenanceRequest.getReturnDate().isBefore(carRental.getRentalReturnDate()))
	                 {
	                    throw new BusinessException(Messages.carMaintenanceCannot);
	                }
	            }
	        }
   }
}