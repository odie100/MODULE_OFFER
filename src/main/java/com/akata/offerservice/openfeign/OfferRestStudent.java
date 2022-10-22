package com.akata.offerservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "STUDENT-SERVICE")
public interface OfferRestStudent {

    @GetMapping(path = "/api/apply/countApplier/{id}")
    int countApplier(@PathVariable("id") Long id);
}
