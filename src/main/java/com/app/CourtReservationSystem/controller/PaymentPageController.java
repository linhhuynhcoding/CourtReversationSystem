package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.enums.PaymentStatus;
import com.app.CourtReservationSystem.service.IPaymentService;
import com.app.CourtReservationSystem.service.Impl.VNPAYService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor()
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentPageController {

    String clientURL = "http://172.19.128.1:3000";

    @Qualifier("VNPAYService")
    final VNPAYService vnpayService;

    final IPaymentService paymentService;


    @GetMapping("/payment")
    public String home(){
        return "createOrder";
    }
    
    @PostMapping("/submitOrder")
    public String submidOrder(@RequestParam("amount") int orderTotal,
                              @RequestParam("orderInfo") String orderInfo,
                              HttpServletRequest request){
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnpayService.createOrder(request, orderTotal, orderInfo, baseUrl);
        return "redirect:" + vnpayUrl;
    }
    
    @GetMapping("/vnpay-payment-return")
    public String paymentCompleted(HttpServletRequest request, Model model){
        int paymentStatus = vnpayService.orderReturn(request);
        
        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");
        
        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);
        model.addAttribute("client_url", clientURL +"/me");

        if (paymentStatus == 1){
            paymentService.updatePaymentStatus(Long.parseLong(orderInfo), PaymentStatus.SUCCESS);
        }
        else {
            paymentService.updatePaymentStatus(Long.parseLong(orderInfo), PaymentStatus.FAIL);
        }
        return "redirect:" + clientURL + "/me";

//        return paymentStatus == 1 ? "orderSuccess" : "orderFail";
    }
    
}
