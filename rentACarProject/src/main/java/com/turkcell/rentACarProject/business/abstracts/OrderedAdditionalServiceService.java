package com.turkcell.rentACarProject.business.abstracts;

import java.util.List;

import com.turkcell.rentACarProject.business.dtos.listdto.OrderedAdditionalServiceListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeleteOrderedAdditionalServiceRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateOrderedAdditionalServiceRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

public interface OrderedAdditionalServiceService {

    DataResult<List<OrderedAdditionalServiceListDto>> getByOrderedAdditional_RentalId(int rentalId) throws BusinessException;
	
	Result add(CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest) throws BusinessException;
    Result update(UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalServiceRequest) throws BusinessException;
	Result delete(DeleteOrderedAdditionalServiceRequest deleteOrderedAdditionalServiceRequest) throws BusinessException;
}
