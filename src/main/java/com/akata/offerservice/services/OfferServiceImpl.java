package com.akata.offerservice.services;

import com.akata.offerservice.dto.OfferRequestDTO;
import com.akata.offerservice.dto.OfferResponseDTO;
import com.akata.offerservice.entities.Offer;
import com.akata.offerservice.mapper.CategoryMapper;
import com.akata.offerservice.mapper.OfferMapper;
import com.akata.offerservice.models.Client;
import com.akata.offerservice.models.OfferModel;
import com.akata.offerservice.openfeign.OfferRestClient;
import com.akata.offerservice.repository.OfferRepository;
import com.akata.offerservice.services.interfaces.CategoryService;
import com.akata.offerservice.services.interfaces.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private OfferMapper offerMapper;

    @Autowired
    private OfferRestClient offerRestClient;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryService categoryService;

    @Override
    public OfferResponseDTO save(OfferModel offerModel) {
        /*Get the client from CLIENT-SERVICE*/
        Client client =  offerRestClient.getClient(offerModel.getClient());
        System.out.println("Client from another service: "+client.getName());

        OfferRequestDTO offerRequestDTO = new OfferRequestDTO();
        offerRequestDTO.setClient(client);
        offerRequestDTO.setApplicant_number(offerModel.getApplicant_number());
        offerRequestDTO.setDeadline(offerModel.getDeadline());
        offerRequestDTO.setDetails(offerModel.getDetails());
        offerRequestDTO.setPost_date(offerModel.getPost_date());
        offerRequestDTO.setTheme(offerModel.getTheme());
        offerRequestDTO.setBudget(offerModel.getBudget());
        offerRequestDTO.setCategory(this.categoryMapper.categoryResponseDTOCategory(this.categoryService
                .getCategory(offerModel.getCategory())));
        offerRequestDTO.setClient_id(offerModel.getClient());
        offerRequestDTO.setSpecification(offerModel.getSpecification());
        offerRequestDTO.setStatus("in progress");

        Offer offer = this.offerMapper.offerRequestDTOOffer(offerRequestDTO);

        return this.offerMapper.offerToOfferResponseDTO(this.offerRepository.save(offer));
    }

    @Override
    public OfferResponseDTO getOffer(Long id) {
        return this.offerMapper.offerToOfferResponseDTO(this.offerRepository.findById(id).get());
    }

    @Override
    public OfferResponseDTO update(Long id, OfferRequestDTO offerRequestDTO) {
        Offer offer = this.offerMapper.offerRequestDTOOffer(offerRequestDTO);
        offer.setOffer_id(id);
        return this.offerMapper.offerToOfferResponseDTO(this.offerRepository.save(offer));
    }

    @Override
    public boolean delete(Long id) {
        try {
            this.offerRepository.deleteById(id);
            return true;
        }catch (DataAccessException e){
            return false;
        }
    }

    @Override
    public List<OfferResponseDTO> getAllOffer() {
        List<Offer> offers = this.offerRepository.findAll();
        for (Offer offer : offers){
            Client client = this.offerRestClient.getClient(offer.getClient_id());
            offer.setClient(client);
        }
        return offers.stream().map(offer -> this.offerMapper.offerToOfferResponseDTO(offer)).collect(Collectors.toList());
    }

    @Override
    public List<OfferResponseDTO> getAllByClientId(Long id) {
        List<Offer> offers = this.offerRepository.findByClientID(id);
        for (Offer offer : offers){
            Client client = this.offerRestClient.getClient(offer.getClient_id());
            offer.setClient(client);
        }
        return offers.stream().map((offer -> this.offerMapper.offerToOfferResponseDTO(offer))).collect(Collectors.toList());
    }

    @Override
    public int updateStatus(Long id) {
        return this.offerRepository.updateStatus(id);
    }
}
