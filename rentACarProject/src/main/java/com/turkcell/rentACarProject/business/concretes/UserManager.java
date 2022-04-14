package com.turkcell.rentACarProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentACarProject.business.abstracts.UserService;
import com.turkcell.rentACarProject.business.constants.Messages;
import com.turkcell.rentACarProject.business.dtos.dto.UserDto;
import com.turkcell.rentACarProject.business.dtos.listdto.UserListDto;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.SuccessDataResult;
import com.turkcell.rentACarProject.dataAccess.abstracts.UserDao;
import com.turkcell.rentACarProject.entities.concretes.User;

@Service
public class UserManager implements UserService{

	private UserDao userDao;
	private ModelMapperService modelMapperService;


	@Autowired
	public UserManager(UserDao userDao, ModelMapperService modelMapperService) {
		this.userDao = userDao;
		this.modelMapperService = modelMapperService;
	}


	@Override
	public DataResult<List<UserListDto>> getAll() throws BusinessException {
		
        List<User> result = this.userDao.findAll();
		
		List<UserListDto> response = result.stream().map(user -> this.modelMapperService
				.forDto().map(user, UserListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<UserListDto>>(response);

	}


	@Override
	public DataResult<UserDto> getByUserId(int id) throws BusinessException {
       
	    checkIfUserIdExists(id);
		
		User user = this.userDao.getById(id);
		
		UserDto response = this.modelMapperService.forDto().map(user, UserDto.class);
		
		return new SuccessDataResult<UserDto>(response);
	}

    private void checkIfUserIdExists(Integer id) throws BusinessException {
		
		if(!this.userDao.existsById(id)) {
			
			throw new BusinessException(Messages.userIdExists);
		}

	}

}
