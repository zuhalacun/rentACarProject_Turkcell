package com.turkcell.rentACarProject.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.turkcell.rentACarProject.business.abstracts.AdditionalServiceService;
import com.turkcell.rentACarProject.business.abstracts.CarRentalService;
import com.turkcell.rentACarProject.business.abstracts.CarService;
import com.turkcell.rentACarProject.business.abstracts.CityService;
import com.turkcell.rentACarProject.business.abstracts.InvoiceService;
import com.turkcell.rentACarProject.business.abstracts.OrderedAdditionalServiceService;
import com.turkcell.rentACarProject.business.abstracts.PaymentService;
import com.turkcell.rentACarProject.business.constants.Messages;
import com.turkcell.rentACarProject.business.dtos.dto.AdditionalServiceDto;
import com.turkcell.rentACarProject.business.dtos.dto.CarDto;
import com.turkcell.rentACarProject.business.dtos.dto.CarRentalDto;
import com.turkcell.rentACarProject.business.dtos.dto.CityDto;
import com.turkcell.rentACarProject.business.dtos.dto.PaymentDto;
import com.turkcell.rentACarProject.business.dtos.listdto.AdditionalServiceListDto;
import com.turkcell.rentACarProject.business.dtos.listdto.BrandListDto;
import com.turkcell.rentACarProject.business.dtos.listdto.OrderedAdditionalServiceListDto;
import com.turkcell.rentACarProject.business.dtos.listdto.PaymentListDto;
import com.turkcell.rentACarProject.business.requests.creates.CreateCardDetailRequest;
import com.turkcell.rentACarProject.business.requests.creates.CreateLateDeliveriesPaymentRequest;
import com.turkcell.rentACarProject.business.requests.creates.CreatePaymentRequest;
import com.turkcell.rentACarProject.business.requests.deletes.DeletePaymentRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateCarRentalRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdateLateDeliveriesRentalCarRequest;
import com.turkcell.rentACarProject.business.requests.updates.UpdatePaymentRequest;
import com.turkcell.rentACarProject.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarProject.core.utilities.fakeServices.BankAdapterService;
import com.turkcell.rentACarProject.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.DataResult;
import com.turkcell.rentACarProject.core.utilities.results.dataResult.SuccessDataResult;
import com.turkcell.rentACarProject.core.utilities.results.result.Result;
import com.turkcell.rentACarProject.core.utilities.results.result.SuccessResult;
import com.turkcell.rentACarProject.dataAccess.abstracts.PaymentDao;
import com.turkcell.rentACarProject.entities.concretes.AdditionalService;
import com.turkcell.rentACarProject.entities.concretes.Brand;
import com.turkcell.rentACarProject.entities.concretes.CarRental;
import com.turkcell.rentACarProject.entities.concretes.Invoice;
import com.turkcell.rentACarProject.entities.concretes.Payment;

@Service
public class PaymentManager implements PaymentService{

	private PaymentDao paymentDao;
	private ModelMapperService modelMapperService;
	private CarRentalService carRentalService;
	private CarService carService;
	private OrderedAdditionalServiceService orderedAdditionalServiceService;
	private CityService cityService;
	private InvoiceService invoiceService;
	private BankAdapterService bankAdapterService;
	private AdditionalServiceService additionalServiceService;
	
	@Autowired
	public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService,CarRentalService carRentalService,
			CarService carService,OrderedAdditionalServiceService orderedAdditionalServiceService,CityService cityService,InvoiceService invoiceService,
			BankAdapterService bankAdapterService,AdditionalServiceService additionalServiceService) {
		this.paymentDao = paymentDao;
		this.modelMapperService = modelMapperService;
		this.carRentalService=carRentalService;
		this.carService=carService;
		this.orderedAdditionalServiceService=orderedAdditionalServiceService;
		this.cityService=cityService;
		this.invoiceService=invoiceService;
		this.bankAdapterService=bankAdapterService;
		this.additionalServiceService=additionalServiceService;
	}

	@Override
	public DataResult<List<PaymentListDto>> getAll() throws BusinessException {
		
		List<Payment> result=this.paymentDao.findAll();

        List<PaymentListDto> response = result.stream().map(payment->this.modelMapperService.forDto().map(payment,PaymentListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<PaymentListDto>>(response);
	}

	@Override
    public DataResult<List<PaymentListDto>> getAllPaymentByCarRental_RentalId(int rentalId) throws BusinessException {

        checkIfExistsByCarRental_RentalId(rentalId);

        List<Payment> payments = this.paymentDao.getAllByCarRental_RentalId(rentalId);

        List<PaymentListDto> result = payments.stream().map(payment -> this.modelMapperService.forDto()
                .map(payment, PaymentListDto.class)).collect(Collectors.toList());
       
        return new SuccessDataResult<>(result, "Payments listed by rental car id, rentalCarId: " + rentalId);
    }

	@Override
	public Result add(CreatePaymentRequest createPaymentRequest) throws BusinessException {
		
		Result result = bankAdapterService.checkIfLimitIsEnough(
						createPaymentRequest.getCardNo(),
						createPaymentRequest.getDay(),
						createPaymentRequest.getMonth(),
						createPaymentRequest.getCvv(),
						createPaymentRequest.getTotalPaymentAmount());
		if (result != null) {
			return result;
		}
		
		
        Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		
		int rentalId= createPaymentRequest.getRentalId();
		
		
		CarRentalDto carRental = carRentalService.getByRentalId(rentalId).getData();
	
		
		double totalPrice = totalPriceCalculator(carRental);
		
		payment.setTotalPaymentAmount(totalPrice);
		
		this.paymentDao.save(payment);
		
		return new SuccessResult(Messages.paymentAdded);
	}

	@Override
	public Result update(UpdatePaymentRequest updatePaymentRequest) throws BusinessException {
		
		checkIfPaymentExistsById(updatePaymentRequest.getId());
		
        Payment payment = this.modelMapperService.forRequest().map(updatePaymentRequest, Payment.class);	
		
		this.paymentDao.save(payment);
		
		return new SuccessResult(Messages.paymentUpdated);
	}

	@Override
	public Result delete(DeletePaymentRequest deletePaymentRequest) throws BusinessException {
		
		checkIfPaymentExistsById(deletePaymentRequest.getId());

        Payment payment = this.modelMapperService.forRequest().map(deletePaymentRequest,Payment.class);

        this.paymentDao.delete(payment);

        return new SuccessResult(Messages.paymentDeleted);
	}
	
	@Override
	public Result addForLateDelivery(CreateLateDeliveriesPaymentRequest createLateDeliveriesPaymentRequest, UpdateLateDeliveriesRentalCarRequest updateLateDeliveriesRentalCarRequest) throws BusinessException {
		
		this.bankAdapterService.checkIfLimitIsEnough(createLateDeliveriesPaymentRequest.getCreatePaymentRequest().getCardNo(), createLateDeliveriesPaymentRequest.getCreatePaymentRequest().getCvv(),
				createLateDeliveriesPaymentRequest.getCreatePaymentRequest().getDay(), createLateDeliveriesPaymentRequest
				.getCreatePaymentRequest().getMonth(), createLateDeliveriesPaymentRequest.getCreatePaymentRequest().getTotalPaymentAmount());
	
		   Payment payment = this.modelMapperService.forRequest().map(createLateDeliveriesPaymentRequest, Payment.class);
			
			int rentalId= createLateDeliveriesPaymentRequest.getCreatePaymentRequest().getRentalId();
			
			
			CarRentalDto carRental = carRentalService.getByRentalId(rentalId).getData();
		
			
			double totalPrice = newTotalPriceCalculator(carRental, updateLateDeliveriesRentalCarRequest.getUpdateCarRentalRequest());
			
			payment.setTotalPaymentAmount(totalPrice);
			
			this.paymentDao.save(payment);
			
			return new SuccessResult(Messages.paymentAdded);
	}
	
	private void checkIfPaymentExistsById(int id) throws BusinessException {
        if(!this.paymentDao.existsById(id))
            throw new BusinessException(Messages.paymentIdExists);
    }

	
    private double totalPriceCalculator(CarRentalDto carRental) throws BusinessException {
		
		double totalPrice = 0.0;

		
		long days = ChronoUnit.DAYS.between( carRental.getRentalDate() , carRental.getRentalReturnDate()) ;
	 
		//aynı gün içinde alınıp bırakılacaksa günü 1 say.
		if(days==0) {
			days=1;
		}
		
		totalPrice+=days* carService.getByCarId(carRental.getCarId()).getData().getCarDailyPrice();
		
		//ek hizmet aldıysa fiyata ekle
		
		List<OrderedAdditionalServiceListDto> orderedAdditionalServiceDtos = orderedAdditionalServiceService
				.getByOrderedAdditional_RentalId(carRental.getRentalId()).getData();

		if (orderedAdditionalServiceDtos.size() > 0) {
			for (OrderedAdditionalServiceListDto orderedAdditionalServiceDto : orderedAdditionalServiceDtos) {
				totalPrice += additionalServiceService.getByAdditionalServiceId(orderedAdditionalServiceDto.getAdditionalServiceId()).getData().getDailyPrice();
		
			}
		}
		
		//aldığı şehir ile bıraktığı şehir aynı değilse total price 'a 750 tl ekle.
		
		if(carRental.getPickUpCity().getCityId() != carRental.getReturnCity().getCityId()) {
			totalPrice += 750;
		}
		return totalPrice;
	}

   private double newTotalPriceCalculator(CarRentalDto carRental, UpdateCarRentalRequest updateCarRentalRequest) throws BusinessException {

	double totalPrice = 0;

	long days = ChronoUnit.DAYS.between( carRental.getRentalReturnDate() , updateCarRentalRequest.getRentalReturnDate());
	
	totalPrice+=days* carService.getByCarId(carRental.getCarId()).getData().getCarDailyPrice();
	
	//ek hizmet aldıysa fiyata ekle
	
	List<OrderedAdditionalServiceListDto> orderedAdditionalServiceDtos = orderedAdditionalServiceService
			.getByOrderedAdditional_RentalId(carRental.getRentalId()).getData();

	if (orderedAdditionalServiceDtos.size() > 0) {
		for (OrderedAdditionalServiceListDto orderedAdditionalServiceDto : orderedAdditionalServiceDtos) {
			totalPrice += additionalServiceService.getByAdditionalServiceId(orderedAdditionalServiceDto.getAdditionalServiceId()).getData().getDailyPrice();
	
		}
	}
	
	//aldığı şehir ile bıraktığı şehir aynı değilse total price 'a 750 tl ekle.
	
	if(carRental.getPickUpCity().getCityId() != updateCarRentalRequest.getReturnCityId()) {
		totalPrice += 750;
	}
	return totalPrice;
} 

 private void checkIfExistsByCarRental_RentalId(int rentalId) throws BusinessException {
     if(!this.paymentDao.existsByCarRental_RentalId(rentalId)){
         throw new BusinessException("Payment not found by rental car id, rentalCarId: " + rentalId);
     }
 }
   
}
		
