package com.turkcell.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACarProject.business.abstracts.AdditionalServiceService;
import com.turkcell.rentACarProject.business.constants.Messages;
import com.turkcell.rentACarProject.business.dtos.dto.AdditionalServiceDto;
import com.turkcell.rentACarProject.business.dtos.listdto.AdditionalServiceListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateAdditionalServiceRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteAdditionalServiceRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateAdditionalServiceRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.SuccessDataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;
import com.turkcell.rentACarProject.core.utilities.results.result.SuccessResult;
import com.turkcell.rentACarProject.dataAccess.abstracts.AdditionalServiceDao;
import com.turkcell.rentACarProject.entities.concretes.AdditionalService;

@Service
public class AdditionalServiceManager implements AdditionalServiceService{

	private AdditionalServiceDao additionalServiceDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService) {

		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<AdditionalServiceListDto>> getAll() throws BusinessException {
		
		List<AdditionalService> result = this.additionalServiceDao.findAll();

        List<AdditionalServiceListDto> response = result.stream().map(additionalService ->
                this.modelMapperService.forDto().map(additionalService, AdditionalServiceListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<AdditionalServiceListDto>>(response);
    }

	@Override
	public DataResult<AdditionalServiceDto> getByAdditionalServiceId(int id) throws BusinessException {
		 
		    checkIfAdditionalServiceExistsById(id);

	        AdditionalService additionalService = this.additionalServiceDao.getById(id);

	        AdditionalServiceDto response = this.modelMapperService.forDto().map(additionalService, AdditionalServiceDto.class);

	        return new SuccessDataResult<AdditionalServiceDto>(response);
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException {
		   
		    checkIfAdditionalServiceExistsByName(createAdditionalServiceRequest.getAdditionalServiceName());

	        AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);

	        this.additionalServiceDao.save(additionalService);

	        return new SuccessResult(Messages.additionalServiceAdded);
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException {
        
		checkIfAdditionalServiceExistsById(updateAdditionalServiceRequest.getAdditionalServiceId());

        AdditionalService additionalService = this.modelMapperService.forRequest().map(updateAdditionalServiceRequest, AdditionalService.class);

        this.additionalServiceDao.save(additionalService);

        return new SuccessResult(Messages.additionalServiceUpdated);
	}

	@Override
	public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException {
		 
		    checkIfAdditionalServiceExistsById(deleteAdditionalServiceRequest.getAdditionalServiceId());

	        AdditionalService additionalService = this.modelMapperService.forRequest().map(deleteAdditionalServiceRequest, AdditionalService.class);

	        this.additionalServiceDao.delete(additionalService);

	        return new SuccessResult(Messages.additionalServiceDeleted);
	}
	
	 private void checkIfAdditionalServiceExistsByName(String serviceName) throws BusinessException {
	       //aynı isimde ek hizmet varsa uyarı ver. 
		 if(this.additionalServiceDao.getByAdditionalServiceName(serviceName)!=null)
	            throw new BusinessException(Messages.additionalServiceNameExists);
	    }

	    private void checkIfAdditionalServiceExistsById(int serviceId) throws BusinessException{
	       //girilen id de ek hizmet yoksa uyarı ver.
	    	if(!this.additionalServiceDao.existsById(serviceId))
	            throw new BusinessException(Messages.additionalServiceIdExists);
	    }

}
