package com.turkcell.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACarProject.business.abstracts.CorporateCustomerService;
import com.turkcell.rentACarProject.business.constants.Messages;
import com.turkcell.rentACarProject.business.dtos.dto.CorporateCustomerDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CorporateCustomerListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCorporateCustomerRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCorporateCustomerRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCorporateCustomerRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.SuccessDataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;
import com.turkcell.rentACarProject.core.utilities.results.result.SuccessResult;
import com.turkcell.rentACarProject.dataAccess.abstracts.CorporateCustomerDao;
import com.turkcell.rentACarProject.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService{

	private CorporateCustomerDao corporateCustomerDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
	
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<CorporateCustomerListDto>> getAll() throws BusinessException {
		
        List<CorporateCustomer> result = this.corporateCustomerDao.findAll();
		
		List<CorporateCustomerListDto> response = result.stream().map(corporateCustomer -> this.modelMapperService
				.forDto().map(corporateCustomer, CorporateCustomerListDto.class)).collect(Collectors.toList());
				
		return new SuccessDataResult<List<CorporateCustomerListDto>>(response);

	}

	@Override
	public DataResult<CorporateCustomerDto> getByCustomerId(int id) throws BusinessException {
		
        checkIfCorporateCustomerIdExists(id);
		
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.getById(id);
		
		CorporateCustomerDto response = this.modelMapperService.forDto().map(corporateCustomer, CorporateCustomerDto.class);
		
		return new SuccessDataResult<CorporateCustomerDto>(response);

	}

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException {

		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
		
		this.corporateCustomerDao.save(corporateCustomer);
		
		return new SuccessResult(Messages.corporateCustomerAdded);

	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws BusinessException {
		
	   checkIfCorporateCustomerIdExists(updateCorporateCustomerRequest.getUserId());
		
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCorporateCustomerRequest, CorporateCustomer.class);
		
		this.corporateCustomerDao.save(corporateCustomer);
		
		return new SuccessResult(Messages.corporateCustomerUpdated);

	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException {
        
		checkIfCorporateCustomerIdExists(deleteCorporateCustomerRequest.getUserId());
		
		this.corporateCustomerDao.deleteById(deleteCorporateCustomerRequest.getUserId());
		
		return new SuccessResult(Messages.corporateCustomerDeleted);
	}
	
	public void checkIfCorporateCustomerIdExists(Integer id) throws BusinessException {
		
		if(!this.corporateCustomerDao.existsById(id)) {
			
			throw new BusinessException(Messages.corporateCustomerIdExists);
		}

	}

}
