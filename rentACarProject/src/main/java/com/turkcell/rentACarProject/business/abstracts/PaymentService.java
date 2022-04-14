package com.turkcell.rentACarProject.business.abstracts;

import java.util.List;

import com.turkcell.rentACarProject.business.dtos.dto.PaymentDto;
import com.turkcell.rentACarProject.business.dtos.listdto.PaymentListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateLateDeliveriesPaymentRequest;
import com.turkcell.rentACarProject.business.requests.creates.CreatePaymentRequest;
import com.turkcell.rentACarProject.business.requests.creates.CreatePosServiceRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeletePaymentRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateLateDeliveriesRentalCarRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdatePaymentRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;

public interface PaymentService {

	DataResult<List<PaymentListDto>> getAll() throws BusinessException;
	DataResult<List<PaymentListDto>> getAllPaymentByCarRental_RentalId(int rentalId) throws BusinessException;
	
	
    Result add(CreatePaymentRequest createPaymentRequest) throws BusinessException;
    Result update(UpdatePaymentRequest updatePaymentRequest) throws BusinessException;
    Result delete(DeletePaymentRequest deletePaymentRequest) throws BusinessException;
    
    Result addForLateDelivery(CreateLateDeliveriesPaymentRequest createLateDeliveriesPaymentRequest,UpdateLateDeliveriesRentalCarRequest updateLateDeliveriesRentalCarRequest) throws BusinessException;
}
