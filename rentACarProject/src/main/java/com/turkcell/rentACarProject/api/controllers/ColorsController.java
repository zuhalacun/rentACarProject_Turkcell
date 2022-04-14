package com.turkcell.rentACarProject.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentACarProject.business.abstracts.ColorService;
import com.turkcell.rentACarProject.business.dtos.dto.ColorDto;
import com.turkcell.rentACarProject.business.dtos.listdto.ColorListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateColorRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteColorRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateColorRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {

		private ColorService colorService;

		@Autowired
		public ColorsController(ColorService colorService) {
			 this.colorService = colorService;
		}

		@GetMapping("/getAll")
		public DataResult<List<ColorListDto>> getAll() throws BusinessException {
			
			return this.colorService.getAll();
		}

		@GetMapping("/getById/{id}")
		public DataResult<ColorDto> getColorById(@PathVariable Integer id) throws BusinessException {
			
			return this.colorService.getById(id);
		}

		@PostMapping("/add")
		public Result add(@RequestBody @Valid CreateColorRequest createColorRequest) throws BusinessException {

			return this.colorService.add(createColorRequest);
		}
		
		@PutMapping("/update")
		public Result update(@RequestBody @Valid UpdateColorRequest updateColorRequest) throws BusinessException {

			return this.colorService.update(updateColorRequest);
		}

		@DeleteMapping("/delete")
		public Result delete(@RequestBody @Valid DeleteColorRequest deleteColorRequest) throws BusinessException {

			return this.colorService.delete(deleteColorRequest);
		}
	}
