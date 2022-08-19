package com.akata.offerservice.controllers;

import com.akata.offerservice.dto.OfferRequestDTO;
import com.akata.offerservice.dto.OfferResponseDTO;
import com.akata.offerservice.mapper.CategoryMapper;
import com.akata.offerservice.models.Client;
import com.akata.offerservice.models.OfferModel;
import com.akata.offerservice.openfeign.OfferRestClient;
import com.akata.offerservice.services.interfaces.CategoryService;
import com.akata.offerservice.services.interfaces.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/offer")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private OfferRestClient offerRestClient;

    @GetMapping(path = "/{id}")
    public OfferResponseDTO get(@PathVariable("id") Long id){
        OfferResponseDTO offerResponseDTO = this.offerService.getOffer(id);
        Client client = this.offerRestClient.getClient(offerResponseDTO.getClient_id());
        offerResponseDTO.setClient(client);
        return offerResponseDTO;
    }

    @GetMapping(path = "/all")
    public List<OfferResponseDTO> getAll(){
        return this.offerService.getAllOffer();
    }

    @PostMapping(path = "/insert")
    public OfferResponseDTO insert(@RequestBody OfferModel offerModel){
        return this.offerService.save(offerModel);
    }

    @DeleteMapping(path = "/delete/{id}")
    public boolean delete(@PathVariable("id") Long id){
        return this.offerService.delete(id);
    }

    @PutMapping(path = "/update/{id}")
    public OfferResponseDTO update(@PathVariable Long id, @RequestBody OfferRequestDTO offerRequestDTO){
        return this.offerService.update(id, offerRequestDTO);
    }

    @GetMapping(path = "/allByClient/{id}")
    public List<OfferResponseDTO> getAllByIdClient(@PathVariable("id") Long id){
        return this.offerService.getAllByClientId(id);
    }

    @PutMapping(path = "/updateStatus/{id}")
    public int updateStatus(@PathVariable("id") Long id){
        return this.offerService.updateStatus(id);
    }
}
