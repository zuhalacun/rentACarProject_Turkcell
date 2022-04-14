package com.turkcell.rentACarProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentACarProject.business.abstracts.CardDetailService;
import com.turkcell.rentACarProject.business.dtos.dto.CardDetailDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CarDamageListDto;
import com.turkcell.rentACarProject.business.dtos.listdto.CardDetailListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCarDamageRequest;
import com.turkcell.rentACarProject.business.requests.creates.CreateCardDetailRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCarDamageRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteCardDetailRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCarDamageRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCardDetailRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

@RestController
@RequestMapping("/api/carddetails")
public class CardDetailController {

	private CardDetailService cardDetailService;

	@Autowired
	public CardDetailController(CardDetailService cardDetailService) {
		this.cardDetailService = cardDetailService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<CardDetailListDto>> getAll() throws BusinessException{
		
		return this.cardDetailService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<CardDetailDto> getById(@RequestParam int id)throws BusinessException{
		
		return this.cardDetailService.getById(id);
	}
	
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCardDetailRequest createCardDetailRequest) throws BusinessException{
		
		return this.cardDetailService.add(createCardDetailRequest);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateCardDetailRequest updateCardDetailRequest)throws BusinessException {
		
		return this.cardDetailService.update(updateCardDetailRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteCardDetailRequest deleteCardDetailRequest) throws BusinessException{
		
		return this.cardDetailService.delete(deleteCardDetailRequest);
	}
}
