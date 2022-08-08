package com.akata.offerservice.openfeign;

import com.akata.offerservice.models.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CLIENT-SERVICE")
public interface OfferRestClient {
    @GetMapping(path = "/api/client/{id}")
    Client getClient(@PathVariable("id") Long id);
}
