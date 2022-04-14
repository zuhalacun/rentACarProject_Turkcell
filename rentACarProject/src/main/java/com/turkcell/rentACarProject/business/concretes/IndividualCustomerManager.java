package com.turkcell.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACarProject.business.abstracts.IndividualCustomerService;
import com.turkcell.rentACarProject.business.constants.Messages;
import com.turkcell.rentACarProject.business.dtos.dto.IndividualCustomerDto;
import com.turkcell.rentACarProject.business.dtos.listdto.IndividualCustomerListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateIndividualCustomerRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteIndividualCustomerRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateIndividualCustomerRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.SuccessDataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;
import com.turkcell.rentACarProject.core.utilities.results.result.SuccessResult;
import com.turkcell.rentACarProject.dataAccess.abstracts.IndividualCustomerDao;
import com.turkcell.rentACarProject.entities.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService{

	private IndividualCustomerDao individualCustomerDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao, ModelMapperService modelMapperService) {
		
		this.individualCustomerDao = individualCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<IndividualCustomerListDto>> getAll() throws BusinessException {
		
        List<IndividualCustomer> result = this.individualCustomerDao.findAll();
		
		List<IndividualCustomerListDto> response = result.stream().map(individualCustomer -> this.modelMapperService
				.forDto().map(individualCustomer, IndividualCustomerListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<IndividualCustomerListDto>>(response);

	}

	@Override
	public DataResult<IndividualCustomerDto> getByCustomerId(int id) throws BusinessException {
		
        checkIfIndividualCustomerIdExists(id);
		
		IndividualCustomer individualCustomer = this.individualCustomerDao.getById(id);
		
		IndividualCustomerDto response = this.modelMapperService.forDto().map(individualCustomer, IndividualCustomerDto.class);
		
		return new SuccessDataResult<IndividualCustomerDto>(response);
	}

	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws BusinessException {
		
        IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
		
		this.individualCustomerDao.save(individualCustomer);
		
		return new SuccessResult(Messages.individualCustomerAdded);

	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws BusinessException {
		
        checkIfIndividualCustomerIdExists(updateIndividualCustomerRequest.getUserId());
		
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class);
		
		this.individualCustomerDao.save(individualCustomer);
		
		return new SuccessResult(Messages.individualCustomerUpdated);

	}

	@Override
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws BusinessException {
		
        checkIfIndividualCustomerIdExists(deleteIndividualCustomerRequest.getUserId());
		
		this.individualCustomerDao.deleteById(deleteIndividualCustomerRequest.getUserId());
		
		return new SuccessResult(Messages.individualCustomerDeleted);
	}
	
	public void checkIfIndividualCustomerIdExists(Integer id) throws BusinessException {
		
		if(!this.individualCustomerDao.existsById(id)) {
			
			throw new BusinessException(Messages.individualCustomerIdExists);
		}

	}


}
