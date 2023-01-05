package com.utkuakgungor.service.impl;

import com.iyzipay.Options;
import com.iyzipay.model.*;
import com.iyzipay.model.Payment;
import com.iyzipay.request.CreatePaymentRequest;
import com.utkuakgungor.entity.*;
import com.utkuakgungor.repository.PaymentRepository;
import com.utkuakgungor.repository.SeatRepository;
import com.utkuakgungor.service.PaymentInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PaymentImpl implements PaymentInterface {

    private final SeatRepository seatRepository;
    private final PaymentRepository paymentRepository;

    public PaymentImpl(SeatRepository seatRepository, PaymentRepository paymentRepository) {
        this.seatRepository = seatRepository;
        this.paymentRepository = paymentRepository;
    }

    private void pay(BigDecimal price,String itemName){
        Options options = new Options();
        options.setApiKey(IyzicoApiEntity.getApiKey()); //TODO:
        options.setSecretKey(IyzicoApiEntity.getSecretKey());
        options.setBaseUrl("https://sandbox-api.iyzipay.com");

        CreatePaymentRequest request = new CreatePaymentRequest();
        request.setLocale(Locale.TR.getValue());
        request.setConversationId("123456789");
        request.setPrice(price);
        request.setPaidPrice(price);
        request.setCurrency(Currency.TRY.name());
        request.setInstallment(1);
        request.setBasketId(itemName);
        request.setPaymentChannel(PaymentChannel.WEB.name());
        request.setPaymentGroup(PaymentGroup.PRODUCT.name());

        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setCardHolderName("John Doe");
        paymentCard.setCardNumber("5528790000000008");
        paymentCard.setExpireMonth("12");
        paymentCard.setExpireYear("2030");
        paymentCard.setCvc("123");
        paymentCard.setRegisterCard(0);
        request.setPaymentCard(paymentCard);

        Buyer buyer = new Buyer();
        buyer.setId("BY789");
        buyer.setName("John");
        buyer.setSurname("Doe");
        buyer.setGsmNumber("+905350000000");
        buyer.setEmail("email@email.com");
        buyer.setIdentityNumber("74300864791");
        buyer.setLastLoginDate("2015-10-05 12:43:35");
        buyer.setRegistrationDate("2013-04-21 15:12:09");
        buyer.setRegistrationAddress("Nidakule Göztepe, Merdivenköy Mah. Bora Sok. No:1");
        buyer.setIp("85.34.78.112");
        buyer.setCity("Istanbul");
        buyer.setCountry("Turkey");
        buyer.setZipCode("34732");
        request.setBuyer(buyer);

        Address shippingAddress = new Address();
        shippingAddress.setContactName("Jane Doe");
        shippingAddress.setCity("Istanbul");
        shippingAddress.setCountry("Turkey");
        shippingAddress.setAddress("Nidakule Göztepe, Merdivenköy Mah. Bora Sok. No:1");
        shippingAddress.setZipCode("34742");
        request.setShippingAddress(shippingAddress);

        Address billingAddress = new Address();
        billingAddress.setContactName("Jane Doe");
        billingAddress.setCity("Istanbul");
        billingAddress.setCountry("Turkey");
        billingAddress.setAddress("Nidakule Göztepe, Merdivenköy Mah. Bora Sok. No:1");
        billingAddress.setZipCode("34742");
        request.setBillingAddress(billingAddress);

        List<BasketItem> basketItems = new ArrayList<>();
        BasketItem firstBasketItem = new BasketItem();
        firstBasketItem.setId(itemName);
        firstBasketItem.setName("Plane Ticket");
        firstBasketItem.setCategory1("Travel");
        firstBasketItem.setCategory2("Flight");
        firstBasketItem.setItemType(BasketItemType.VIRTUAL.name());
        firstBasketItem.setPrice(price);
        basketItems.add(firstBasketItem);
        request.setBasketItems(basketItems);

        Payment paymentResponse = Payment.create(request, options);
        PaymentEntity paymentEntity=new PaymentEntity();
        paymentEntity.setBankResponse(paymentResponse.getPaymentStatus());
        paymentEntity.setPrice(price);
        paymentRepository.save(paymentEntity);
    }

    @Override
    public boolean pay(PayEntity payEntity) throws Exception {
        SeatEntity seatEntity = seatRepository.findByFlightIDAndSeatNumber(payEntity.getFlightID(), payEntity.getSeatNumber());
        if (seatEntity == null) {
            throw new Exception("Seat not found");
        }
        if (!seatEntity.getAvailability()) {
            throw new Exception("Seat is not available");
        }
        pay(seatEntity.getPrice(), payEntity.getFlightID()+"/"+ payEntity.getSeatNumber());
        seatEntity.setAvailability(false);
        seatRepository.save(seatEntity);
        return true;
    }
}
