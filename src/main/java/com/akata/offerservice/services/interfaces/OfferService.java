package com.akata.offerservice.services.interfaces;

import com.akata.offerservice.dto.OfferRequestDTO;
import com.akata.offerservice.dto.OfferResponseDTO;
import com.akata.offerservice.models.OfferModel;

import java.util.List;

public interface OfferService {
    OfferResponseDTO save(OfferModel offerModel);

    OfferResponseDTO getOffer(Long id);

    OfferResponseDTO update(Long id, OfferRequestDTO offerRequestDTO);

    boolean delete(Long id);

    List<OfferResponseDTO> getAllOffer();

    List<OfferResponseDTO> getAllByClientId(Long id);

    int updateStatus(Long id);

}
