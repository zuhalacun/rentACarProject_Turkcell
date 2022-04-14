package com.turkcell.rentACarProject.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.turkcell.rentACarProject.business.abstracts.CarMaintenanceService;
import com.turkcell.rentACarProject.business.abstracts.CarRentalService;
import com.turkcell.rentACarProject.business.abstracts.CarService;
import com.turkcell.rentACarProject.business.abstracts.PaymentService;
import com.turkcell.rentACarProject.business.constants.Messages;
import com.turkcell.rentACarProject.business.dtos.dto.CarRentalDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CarMaintenanceListDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CarRentalListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCarRentalRequest;
import com.turkcell.rentACarProject.business.requests.creates.CreateLateDeliveriesPaymentRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCarRentalRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCarRentalRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateLateDeliveriesRentalCarRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.SuccessDataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.ErrorResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;
import com.turkcell.rentACarProject.core.utilities.results.result.SuccessResult;
import com.turkcell.rentACarProject.dataAccess.abstracts.CarRentalDao;
import com.turkcell.rentACarProject.entities.concretes.CarRental;

@Service
public class CarRentalManager implements CarRentalService{

	private CarRentalDao carRentalDao;
	private ModelMapperService modelMapperService;
	private CarMaintenanceService carMaintenanceService;
	private CarService carService;
	
	@Autowired
	public CarRentalManager(CarRentalDao carRentalDao,
			ModelMapperService modelMapperService,
			@Lazy CarMaintenanceService carMaintenanceService,CarService carService) {
		this.carRentalDao = carRentalDao;
		this.modelMapperService = modelMapperService;
		this.carMaintenanceService = carMaintenanceService;
		this.carService=carService;
		
	}

	@Override
	public DataResult<List<CarRentalListDto>> getAll() throws BusinessException {
		
		List<CarRental> result = this.carRentalDao.findAll();

		List<CarRentalListDto> response = result.stream()
				.map(carRental -> this.modelMapperService.forDto().map(carRental, CarRentalListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<CarRentalListDto>>(response);
	}

	@Override
	public List<CarRentalListDto> getByCarId(int id) throws BusinessException {
	
		List<CarRental> result = this.carRentalDao.getByCarId(id);

		List<CarRentalListDto> response = result.stream()
				.map(carRental -> this.modelMapperService.forDto().map(carRental, CarRentalListDto.class))
				.collect(Collectors.toList());

		return response;

	}

	@Override
	public DataResult<CarRentalDto> getByRentalId(int id) throws BusinessException {
		
		checkIfCarRentalExistsById(id);
		CarRental result = this.carRentalDao.getById(id);

		CarRentalDto response = this.modelMapperService.forDto().map(result, CarRentalDto.class);

		return new SuccessDataResult<CarRentalDto>(response);
	}

	@Override
	public Result addForIndividualCustomer(CreateCarRentalRequest createCarRentalRequest) throws BusinessException {
		
		checkIfCarExist(createCarRentalRequest.getCarId());
		checkIfDatesAreCorrect(createCarRentalRequest.getRentalDate(),createCarRentalRequest.getRentalReturnDate());
		checkIfInMaintenance(createCarRentalRequest);
		
		CarRental carRental = this.modelMapperService.forRequest().map(createCarRentalRequest, CarRental.class);


		this.carRentalDao.save(carRental);

		return new SuccessResult(Messages.carRentalAdded);

	}

	@Override
	public Result addForCorporateCustomer(CreateCarRentalRequest createCarRentalRequest) throws BusinessException {
		
		checkIfCarExist(createCarRentalRequest.getCarId());
		checkIfDatesAreCorrect(createCarRentalRequest.getRentalDate(),createCarRentalRequest.getRentalReturnDate());
		checkIfInMaintenance(createCarRentalRequest);

		CarRental carRental = this.modelMapperService.forRequest().map(createCarRentalRequest, CarRental.class);

		
		this.carRentalDao.save(carRental);

		return new SuccessResult(Messages.carRentalAdded);
	}
	
	@Override
	public Result update(UpdateCarRentalRequest updateCarRentalRequest) throws BusinessException {
		
		checkIfCarRentalExistsById(updateCarRentalRequest.getRentalId());
		checkIfDatesAreCorrect(updateCarRentalRequest.getRentalDate(),updateCarRentalRequest.getRentalReturnDate());
		
		CarRental carRental = this.modelMapperService.forRequest().map(updateCarRentalRequest, CarRental.class);

		updateOperation(carRental, updateCarRentalRequest);
		
		this.carRentalDao.save(carRental);

		return new SuccessResult(Messages.carRentalUpdated);

	}

	@Override
	public Result delete(DeleteCarRentalRequest deleteCarRentalRequest) throws BusinessException {
		
		 checkIfCarRentalExistsById(deleteCarRentalRequest.getRentalId());

	        CarRental carRental = this.carRentalDao.getById(deleteCarRentalRequest.getRentalId());

	        this.carRentalDao.delete(carRental);

	        return new SuccessResult(Messages.carRentalDeleted);

	}
	
	@Override
	public Result lateDeliveriesUpdate(int id,UpdateLateDeliveriesRentalCarRequest updateLateDeliveriesRentalCarRequest) throws BusinessException {
        
		checkIfCarRentalExistsById(id);
		
		CarRental carRental = this.carRentalDao.getByRentalId(id);

		checkIfDates(carRental, updateLateDeliveriesRentalCarRequest.getUpdateCarRentalRequest());

		updateOperation(carRental, updateLateDeliveriesRentalCarRequest.getUpdateCarRentalRequest());
		
		carRentalDao.save(carRental);

		return new SuccessResult(Messages.carRentalUpdated);
	}
	
   private void checkIfDates(CarRental carRental, UpdateCarRentalRequest updateCarRentalRequest) throws BusinessException {
		
		if(carRental.getRentalReturnDate().isAfter(updateCarRentalRequest.getRentalReturnDate())) {
			throw new BusinessException(Messages.carRentalFailReturnDate);
		}
		
	}
	
	
	
	 private void checkIfCarRentalExistsById(int id) throws BusinessException{
	        if(!this.carRentalDao.existsById(id))
	            throw new BusinessException(Messages.carRentalIdExists);
	    }
		
	  private void checkIfCarExist(int carId) throws BusinessException {

			if (this.carService.getByCarId(carId) == null) {
				throw new BusinessException(Messages.carIdExists);
             }
		}


		private void checkIfInMaintenance(CreateCarRentalRequest createCarRentalRequest) throws BusinessException {

			List<CarMaintenanceListDto> result = this.carMaintenanceService.getByCarId(createCarRentalRequest.getCarId())
					.getData();

			if (result == null) {
				return;
			}

			for (CarMaintenanceListDto carMaintenanceDto : result) {
				if ((carMaintenanceDto.getReturnDate() != null)
						&& (createCarRentalRequest.getRentalDate().isBefore(carMaintenanceDto.getReturnDate())
								|| createCarRentalRequest.getRentalReturnDate().isBefore(carMaintenanceDto.getReturnDate()))) {
					throw new BusinessException(Messages.carRentalCannot);
				}
				if (carMaintenanceDto.getReturnDate() == null) {
					throw new BusinessException(Messages.carRentalCannot);
				}
			}
		}

		private void checkIfDatesAreCorrect(LocalDate startDate,LocalDate endDate) throws BusinessException{
	        if(startDate.isAfter(endDate))
	            throw new BusinessException(Messages.carRentalDateControl);
	    }

		private void updateOperation(CarRental carRental, UpdateCarRentalRequest updateCarRentalRequest) {

			carRental.setRentalReturnDate(updateCarRentalRequest.getRentalDate());
			carRental.setReturnedKilometer(updateCarRentalRequest.getReturnedKilometer());
 }
}

