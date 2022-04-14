package com.turkcell.rentACarProject.business.abstracts;

import java.util.List;

import com.turkcell.rentACarProject.business.dtos.dto.CardDetailDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CardDetailListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCardDetailRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCardDetailRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCardDetailRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

public interface CardDetailService {

	DataResult<List<CardDetailListDto>> getAll() throws BusinessException;
	DataResult<CardDetailDto> getById(int id) throws BusinessException;
	
	Result add(CreateCardDetailRequest createCardDetailRequest) throws BusinessException;
	Result update(UpdateCardDetailRequest uptadeCardDetailRequest) throws BusinessException;
	Result delete(DeleteCardDetailRequest deleteCardDetailRequest) throws BusinessException;
}
