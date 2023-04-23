package com.cm.tdbConsumer.client;


import com.cm.tdbConsumer.dto.ProductOrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-api", url = "https://api.hurdle.bio/orders/v2")
public interface LabPartnerClient {
    @PostMapping(value = "/api/placeOrder",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ProductOrderDto placeOrder(@RequestBody ProductOrderDto orderDto);

    //calling lab api
    //and saving status in db by sent_to_lab
}
