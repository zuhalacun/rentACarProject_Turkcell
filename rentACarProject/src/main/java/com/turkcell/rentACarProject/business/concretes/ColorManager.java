package com.turkcell.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACarProject.business.abstracts.ColorService;
import com.turkcell.rentACarProject.business.constants.Messages;
import com.turkcell.rentACarProject.business.dtos.dto.ColorDto;
import com.turkcell.rentACarProject.business.dtos.listdto.ColorListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateColorRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteColorRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateColorRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.SuccessDataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;
import com.turkcell.rentACarProject.core.utilities.results.result.SuccessResult;
import com.turkcell.rentACarProject.dataAccess.abstracts.ColorDao;
import com.turkcell.rentACarProject.entities.concretes.Color;

@Service
public class ColorManager implements ColorService{

	private ColorDao colorDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ColorListDto>> getAll() throws BusinessException {
		 List<Color> result = this.colorDao.findAll();
	        List<ColorListDto> response = result.stream().map(color->this.modelMapperService.forDto().map(color,ColorListDto.class)).collect(Collectors.toList());
	        return new SuccessDataResult<List<ColorListDto>>(response);
	}

	@Override
	public DataResult<ColorDto> getById(int colorId) throws BusinessException {
		  
		    checkIfColorExistsById(colorId);

	        Color color = colorDao.getById(colorId);

	        ColorDto colorDto = this.modelMapperService.forDto().map(color,ColorDto.class);

	        return new SuccessDataResult<ColorDto>(colorDto);
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) throws BusinessException {
		
		    checkIfColorExistsByName(createColorRequest.getColorName());

	        Color color = this.modelMapperService.forRequest().map(createColorRequest,Color.class);

	        this.colorDao.save(color);

	        return new SuccessResult(Messages.colorAdded);
	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) throws BusinessException {
		 
		   checkIfColorExistsById(updateColorRequest.getColorId());
	       

	        Color color = this.modelMapperService.forRequest().map(updateColorRequest,Color.class);

	        this.colorDao.save(color);

	        return new SuccessResult(Messages.colorUpdated);
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) throws BusinessException {
		
		    checkIfColorExistsById(deleteColorRequest.getColorId());

	        Color color = this.modelMapperService.forRequest().map(deleteColorRequest,Color.class);

	        this.colorDao.delete(color);

	        return new SuccessResult(Messages.colorDeleted);
	}

	public void checkIfColorExistsByName(String name) throws BusinessException {
        if(this.colorDao.getByName(name)!=null)
            throw new BusinessException(Messages.colorNameExists);
    }

    public void checkIfColorExistsById(int id) throws BusinessException {
        if(!this.colorDao.existsById(id))
            throw new BusinessException(Messages.colorIdExists);
    }
}
