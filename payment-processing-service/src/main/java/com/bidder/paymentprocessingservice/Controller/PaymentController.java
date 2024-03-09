package com.bidder.paymentprocessingservice.Controller;


import com.bidder.paymentprocessingservice.Entity.Payment;
import com.bidder.paymentprocessingservice.Service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(summary = "Get All payments done in the server/database",
            description = "With Recieve all the payments done",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            })

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        try {
            List<Payment> payments = paymentService.findAllPayments();
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Get a payment based on the orderID",
            description = "Retrieve a specific payment by OrderId",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            })
    @GetMapping("/{orderId}")
    public ResponseEntity<Payment> getPaymentByOrderId(@PathVariable Long orderId) {
        try {
            Payment payment = paymentService.findPaymentById(orderId);
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get all orders done by a single user",
            description = "With Recieve all the payments done by the given users id ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payment>> getPaymentsByUserId(@PathVariable Long userId) {
        try {
            List<Payment> payments = paymentService.findPaymentsByUserId(userId);
            if (payments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Retrieves All payments by AuctionId",
            description = "Returns all payments done for a specific Auction",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            })
    @GetMapping("/auction/{auctionId}")
    public ResponseEntity<List<Payment>> getPaymentsByAuctionId(@PathVariable Long auctionId) {
        try {
            List<Payment> payments = paymentService.findPaymentsByAuctionId(auctionId);
            if (payments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Create a payment and store it into a database",
            description = "",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successful Creation",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            })
    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        try {
            Payment createdPayment = paymentService.savePayment(payment);
            return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Edit a payment by OrderId",
            description = "",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successful Update",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            })
    @PutMapping("/{orderId}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long orderId, @RequestBody Payment paymentDetails) {
        try {
            Payment updatedPayment = paymentService.updatePayment(orderId, paymentDetails);
            return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Delete an order by ID",
            description = "",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successful Deletion",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            })
    @DeleteMapping("/{orderId}")
    public ResponseEntity<HttpStatus> deletePayment(@PathVariable Long orderId) {
        try {
            paymentService.deletePayment(orderId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

