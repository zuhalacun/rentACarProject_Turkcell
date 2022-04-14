package com.turkcell.rentACarProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.turkcell.rentACarProject.business.abstracts.BrandService;
import com.turkcell.rentACarProject.business.dtos.dto.BrandDto;
import com.turkcell.rentACarProject.business.dtos.listdto.BrandListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateBrandRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteBrandRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateBrandRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {
	
	private BrandService brandService;

	@Autowired
	public BrandsController(BrandService brandService) {
		
		this.brandService = brandService;
	}

	@GetMapping("/getAll")
	public DataResult<List<BrandListDto>> getAll() throws BusinessException {
		
		return this.brandService.getAll();
	}

	@GetMapping("/getByBrandId/{brandId}")
	public DataResult<BrandDto> getByBrandId(@RequestParam("brandId") int brandId) throws BusinessException {
		
		return this.brandService.getByBrandId(brandId);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest) throws BusinessException {

		return this.brandService.add(createBrandRequest);
	}


	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) throws BusinessException {
		
		return this.brandService.update(updateBrandRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteBrandRequest deleteBrandRequest) throws BusinessException {
		
		return this.brandService.delete(deleteBrandRequest);
	}

}
