package com.bidder.paymentprocessingservice.Service;


import com.bidder.paymentprocessingservice.Entity.Payment;
import com.bidder.paymentprocessingservice.Repository.PaymentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment findPaymentById(Long orderId) {
        return paymentRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found for this id :: " + orderId));
    }

    public List<Payment> findPaymentsByUserId(Long userId) {
        return paymentRepository.findByUserId(userId);
    }

    public List<Payment> findPaymentsByAuctionId(Long auctionId) {
        return paymentRepository.findByAuctionId(auctionId);
    }

    public Payment savePayment(Payment payment) {
        if (Boolean.TRUE.equals(payment.getExpressShipping())) {
            double expressShippingFee = 20.0; // Add an extra charge for express shipping
            payment.setAmount(payment.getAmount() + expressShippingFee);
        }
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Long orderId, Payment paymentDetails) {
        Payment payment = paymentRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found for this id :: " + orderId));

        payment.setUserId(paymentDetails.getUserId());
        payment.setCreditCard(paymentDetails.getCreditCard());
        payment.setExpressShipping(paymentDetails.getExpressShipping());
        payment.setAuctionId(paymentDetails.getAuctionId());
        payment.setAmount(paymentDetails.getAmount());

        return paymentRepository.save(payment);
    }

    public void deletePayment(Long orderId) {
        Payment payment = paymentRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found for this id :: " + orderId));

        paymentRepository.delete(payment);
    }
}
