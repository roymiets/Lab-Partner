package com.cm.tdbWebhook.controller;


import com.cm.tdbWebhook.service.OrderWebHookService;
import com.cm.tdbWebhook.dto.ProductOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderWebHook {
    private final OrderWebHookService orderService;

    @PutMapping("/labpartner/webhook")
    public ResponseEntity<String> updateOrder(@RequestBody ProductOrderDto productOrderDto){
        return new ResponseEntity<>(orderService.updateOrderStatus(productOrderDto), HttpStatus.OK);
    }
}
