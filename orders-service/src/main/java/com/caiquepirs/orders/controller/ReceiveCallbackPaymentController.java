package com.caiquepirs.orders.controller;

import com.caiquepirs.orders.client.gateway.impl.ClientBankingServiceImpl;
import com.caiquepirs.orders.controller.dto.ReceiveCallbackPaymentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders/callback-payment")
public class ReceiveCallbackPaymentController {

    private final ClientBankingServiceImpl clientBankingService;

    @PostMapping
    public ResponseEntity<Object> updateStatusPayment(
            @RequestBody ReceiveCallbackPaymentDTO receiveCallbackPaymentDTO,
            @RequestHeader(required = true, name = "apiKey") String apiKey){

       // validation logic to validate apikey

        clientBankingService.updateStatusPayment(receiveCallbackPaymentDTO);
        return ResponseEntity.ok().build();
    }



}
