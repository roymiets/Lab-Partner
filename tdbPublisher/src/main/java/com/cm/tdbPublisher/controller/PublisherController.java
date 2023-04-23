package com.cm.tdbPublisher.controller;

import com.cm.tdbPublisher.dto.GenericResponse;
import com.cm.tdbPublisher.dto.ProductOrderDto;
import com.cm.tdbPublisher.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PublisherController {


    private final PublisherService orderService;

    @PostMapping("/order")
    public ResponseEntity<GenericResponse> placeOrder(@RequestBody ProductOrderDto productOrderDto){
        return new ResponseEntity<>(orderService.placeOrder(productOrderDto), HttpStatus.OK);
    }
}
