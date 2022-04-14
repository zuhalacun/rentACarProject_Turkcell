package com.turkcell.rentACarProject.business.abstracts;

import java.util.List;

import com.turkcell.rentACarProject.business.dtos.dto.UserDto;
import com.turkcell.rentACarProject.business.dtos.listdto.UserListDto;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;

public interface UserService {

	DataResult<List<UserListDto>> getAll() throws BusinessException;
	DataResult<UserDto> getByUserId(int id) throws BusinessException;
}
