package com.turkcell.rentACarProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentACarProject.business.abstracts.UserService;
import com.turkcell.rentACarProject.business.dtos.dto.UserDto;
import com.turkcell.rentACarProject.business.dtos.listdto.UserListDto;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private UserService userService;
	
	@Autowired
	public UsersController(UserService userService) {
        this.userService = userService;
	}

	@GetMapping("/getAll")
	DataResult<List<UserListDto>> getAll() throws BusinessException{
		
		return this.userService.getAll();
	}

	@GetMapping("/getByUserId/{userId}")
	DataResult<UserDto> getByUserId(@RequestParam("userId") Integer id) throws BusinessException{
		
		return this.userService.getByUserId(id);
	}
  
}
