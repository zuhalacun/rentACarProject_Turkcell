package com.turkcell.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.turkcell.rentACarProject.business.abstracts.CarRentalService;
import com.turkcell.rentACarProject.business.abstracts.OrderedAdditionalServiceService;
import com.turkcell.rentACarProject.business.constants.Messages;
import com.turkcell.rentACarProject.business.dtos.listdto.OrderedAdditionalServiceListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteOrderedAdditionalServiceRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateOrderedAdditionalServiceRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.SuccessDataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;
import com.turkcell.rentACarProject.core.utilities.results.result.SuccessResult;
import com.turkcell.rentACarProject.dataAccess.abstracts.OrderedAdditionalServiceDao;
import com.turkcell.rentACarProject.entities.concretes.OrderedAdditionalService;

@Service
public class OrderedAdditionalServiceManager implements OrderedAdditionalServiceService{

	private OrderedAdditionalServiceDao orderedAdditionalServiceDao;
	private ModelMapperService modelMapperService;
	private CarRentalService carRentalService;

		
	@Autowired
	public OrderedAdditionalServiceManager(OrderedAdditionalServiceDao orderedAdditionalServiceDao, ModelMapperService modelMapperService,@Lazy CarRentalService carRentalService) {

		this.orderedAdditionalServiceDao = orderedAdditionalServiceDao;
		this.modelMapperService = modelMapperService;
		this.carRentalService=carRentalService;
	
	}
	
	@Override
	public DataResult<List<OrderedAdditionalServiceListDto>> getByOrderedAdditional_RentalId(int rentalId)
			throws BusinessException {
		
		checkIfCarRentalExistsById(rentalId);
        checkIsExistsByRentalCarId(rentalId);

        List<OrderedAdditionalService> orderedAdditionals = this.orderedAdditionalServiceDao.getAllByCarRental_RentalId(rentalId);

        List<OrderedAdditionalServiceListDto> result = orderedAdditionals.stream().map(orderedAdditional -> this.modelMapperService
                .forDto().map(orderedAdditional, OrderedAdditionalServiceListDto.class)).collect(Collectors.toList());

        for(int i = 0; i < result.size(); i++){
            result.get(i).setRentalId(orderedAdditionals.get(i).getCarRental().getRentalId());
        }

        return new SuccessDataResult<>(result, "Ordered Additional Service of the Rented Car listed by RentalCarId: " + rentalId);
    }


	@Override
	public Result add(CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest) throws BusinessException {
		
		checkIfCarRentalExistsById(createOrderedAdditionalServiceRequest.getRentalId());
		
		OrderedAdditionalService orderedAdditionalService = this.modelMapperService.forRequest().map(createOrderedAdditionalServiceRequest, OrderedAdditionalService.class);
		
		this.orderedAdditionalServiceDao.save(orderedAdditionalService);
		
		return new SuccessResult(Messages.orderedAdditionalServiceAdded);
	}


	@Override
	public Result update(UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalServiceRequest) throws BusinessException {
		
		checkIfCarRentalExistsById(updateOrderedAdditionalServiceRequest.getRentalId());
        checkIsExistsByRentalCarId(updateOrderedAdditionalServiceRequest.getRentalId());
		
        OrderedAdditionalService orderedAdditionalService = this.modelMapperService.forRequest().map(updateOrderedAdditionalServiceRequest, OrderedAdditionalService.class);
		
		this.orderedAdditionalServiceDao.save(orderedAdditionalService);
		
		return new SuccessResult(Messages.orderedAdditionalServiceUpdated);
	}

	@Override
	public Result delete(DeleteOrderedAdditionalServiceRequest deleteOrderedAdditionalServiceRequest) throws BusinessException {
		
		checkIfOrderedAdditionalExistsById(deleteOrderedAdditionalServiceRequest.getId());
		
       OrderedAdditionalService orderedAdditionalService = this.modelMapperService.forRequest().map(deleteOrderedAdditionalServiceRequest, OrderedAdditionalService.class);
		
		this.orderedAdditionalServiceDao.delete(orderedAdditionalService);
		
		return new SuccessResult(Messages.orderedAdditionalServiceDeleted);
	}
	 private void checkIfCarRentalExistsById(int rentalId) throws BusinessException {

			if (this.carRentalService.getByRentalId(rentalId) == null) {
				throw new BusinessException(Messages.carRentalIdExists);
          }
		}

	 private void checkIsExistsByRentalCarId(int rentalId) throws BusinessException {
	        if(!this.orderedAdditionalServiceDao.existsByCarRental_RentalId(rentalId)){
	            throw new BusinessException("There is a car rental, but there is no ordered additional service for this car rental");
	        }
	    }

	private void checkIfOrderedAdditionalExistsById(int id) throws BusinessException {
		
		if(this.orderedAdditionalServiceDao.existsById(id)) {
			throw new BusinessException(Messages.orderedAdditionalServiceIdExists);
		}
	}

}
