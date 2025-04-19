package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.payment.PaymentPayload;
import com.app.CourtReservationSystem.dto.payment.PaymentResult;
import com.app.CourtReservationSystem.enums.PaymentFor;
import com.app.CourtReservationSystem.enums.PaymentMethod;
import com.app.CourtReservationSystem.enums.PaymentStatus;
import com.app.CourtReservationSystem.exception.APIException;
import com.app.CourtReservationSystem.exception.ResourceNotFoundException;
import com.app.CourtReservationSystem.model.Booking;
import com.app.CourtReservationSystem.model.Payment;
import com.app.CourtReservationSystem.repository.BookingRepository;
import com.app.CourtReservationSystem.repository.OrderRepository;
import com.app.CourtReservationSystem.repository.PaymentRepository;
import com.app.CourtReservationSystem.service.IPaymentMethodService;
import com.app.CourtReservationSystem.service.IPaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
//@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentService implements IPaymentService {
    private Map<PaymentMethod, IPaymentMethodService> paymentMethodServiceMap;
    private PaymentRepository paymentRepository;
    private BookingRepository bookingRepository;
    private OrderRepository orderRepository;

    public PaymentService(
            VNPAYService vnpayService,
            CODSerivce codSerivce,
            PaymentRepository paymentRepository,
            BookingRepository bookingRepository,
            OrderRepository orderRepository
    ) {
        paymentMethodServiceMap = new HashMap<>();
        this.paymentMethodServiceMap.put(PaymentMethod.VNPAY, vnpayService);
        this.paymentMethodServiceMap.put(PaymentMethod.COD, codSerivce);

        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
        this.orderRepository = orderRepository;
    }

//    public PaymentService(
//            PaymentRepository paymentRepository,
//            BookingRepository bookingRepository,
//            OrderRepository orderRepository) {
//        paymentMethodServiceMap = new HashMap<>();
//
//        this.paymentRepository = paymentRepository;
//        this.bookingRepository = bookingRepository;
//        this.orderRepository = orderRepository;
//    }

    @Override
    @Transactional
    public Payment createPayment(PaymentPayload payload) {
        Payment payment = new Payment();
        payment.setAmount(payload.getAmount());
        payment.setMethodPayment(payload.getPaymentMethod());
        paymentRepository.save(payment);
        return payment;
    }

    @Override
    @Transactional
    public PaymentResult handlePaymentBooking(HttpServletRequest request, PaymentPayload payload) {
        // Chỉ chấp nhận thanh toán cho Booking
        if (payload.getPaymentFor() != PaymentFor.BOOKING) {
            throw new APIException("Thông tin thanh toán không phù hợp!", HttpStatus.BAD_REQUEST);
        }

        var paymentResult = new PaymentResult();
        var booking = this.bookingRepository.findById(payload.getId()).orElseThrow(() -> new ResourceNotFoundException("Booking", "id", payload.getId()));
        var isPaid = (booking.getPayment() != null);
        var haveOrder = (booking.getOrder() == null);
        var amount = booking.getTotal();

        if (isPaid) {
            throw new APIException("Đã thanh toán!", HttpStatus.BAD_REQUEST);
        }

        if (!haveOrder) {
            amount += booking.getOrder().getTotal();
        }

        // Tạo Payment
        var payment = new Payment();
        payment.setAmount(amount);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setMethodPayment(payload.getPaymentMethod());
        payment.setBooking(booking);
        if (haveOrder) {
            payment.setOrder(booking.getOrder());
        }
        paymentRepository.save(payment);

        // Chuyển đến function xử lý thanh toán theo phương thức
        return paymentMethodServiceMap.get(payload.getPaymentMethod()).process(request, payment);
    }
}
