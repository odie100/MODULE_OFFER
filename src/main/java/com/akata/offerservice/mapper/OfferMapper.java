package com.akata.offerservice.mapper;

import com.akata.offerservice.dto.OfferRequestDTO;
import com.akata.offerservice.dto.OfferResponseDTO;
import com.akata.offerservice.entities.Offer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfferMapper {
    OfferResponseDTO offerToOfferResponseDTO(Offer offer);
    Offer offerRequestDTOOffer (OfferRequestDTO offerRequestDTO);
    Offer offerResponseDTOOffer(OfferResponseDTO offerResponseDTO);
}
