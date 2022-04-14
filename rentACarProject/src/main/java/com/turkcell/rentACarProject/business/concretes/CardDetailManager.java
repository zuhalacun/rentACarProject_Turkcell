package com.turkcell.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACarProject.business.abstracts.CardDetailService;
import com.turkcell.rentACarProject.business.abstracts.UserService;
import com.turkcell.rentACarProject.business.constants.Messages;
import com.turkcell.rentACarProject.business.dtos.dto.CardDetailDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CardDetailListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCardDetailRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCardDetailRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCardDetailRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.SuccessDataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;
import com.turkcell.rentACarProject.core.utilities.results.result.SuccessResult;
import com.turkcell.rentACarProject.dataAccess.abstracts.CardDetailDao;
import com.turkcell.rentACarProject.entities.concretes.CardDetail;

@Service
public class CardDetailManager implements CardDetailService{

	private CardDetailDao cardDetailDao;
	private ModelMapperService modelMapperService;
	private UserService userService;
	
	@Autowired
	public CardDetailManager(CardDetailDao cardDetailDao, ModelMapperService modelMapperService,UserService userService) {
		this.cardDetailDao = cardDetailDao;
		this.modelMapperService = modelMapperService;
		this.userService=userService;
	}

	@Override
	public DataResult<List<CardDetailListDto>> getAll() throws BusinessException {
		
		List<CardDetail> result = this.cardDetailDao.findAll();

        List<CardDetailListDto> response = result.stream().map(cardDetail->this.modelMapperService.forDto().map(cardDetail,CardDetailListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<CardDetailListDto>>(response);
	}

	@Override
	public DataResult<CardDetailDto> getById(int id) throws BusinessException {
		
		checkIfCardDetailExistsById(id);

		CardDetail cardDetail = this.cardDetailDao.getById(id);

		CardDetailDto response = this.modelMapperService.forDto().map(cardDetail,CardDetailDto.class);

        return new SuccessDataResult<CardDetailDto>(response);
	}

	@Override
	public Result add(CreateCardDetailRequest createCardDetailRequest) throws BusinessException {
		
		checkIfUserExistsById(createCardDetailRequest.getUserId());
		
		CardDetail cardDetail = this.modelMapperService.forRequest().map(createCardDetailRequest,CardDetail.class);

        this.cardDetailDao.save(cardDetail);

        return new SuccessResult(Messages.cardDetailAdded);
	}

	@Override
	public Result update(UpdateCardDetailRequest updateCardDetailRequest) throws BusinessException {
		
		checkIfCardDetailExistsById(updateCardDetailRequest.getId());

		CardDetail cardDetail = this.modelMapperService.forRequest().map(updateCardDetailRequest,CardDetail.class);

        this.cardDetailDao.save(cardDetail);

        return new SuccessResult(Messages.cardDetailUpdated);
	}

	@Override
	public Result delete(DeleteCardDetailRequest deleteCardDetailRequest) throws BusinessException {
		
		checkIfCardDetailExistsById(deleteCardDetailRequest.getId());

		CardDetail cardDetail= this.modelMapperService.forRequest().map(deleteCardDetailRequest,CardDetail.class);

        this.cardDetailDao.delete(cardDetail);

        return new SuccessResult(Messages.cardDetailDeleted);
	}
	
	private void checkIfCardDetailExistsById(int id) throws BusinessException {
        if(!this.cardDetailDao.existsById(id))
            throw new BusinessException(Messages.cardDetailIdExists);
    }
	private void checkIfUserExistsById(int id) throws BusinessException{
		if(!this.userService.getByUserId(id).isSuccess())
			throw new BusinessException(Messages.userIdExists);
	}
}
