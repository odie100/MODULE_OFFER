package com.akata.offerservice.services;

import com.akata.offerservice.dto.OfferRequestDTO;
import com.akata.offerservice.dto.OfferResponseDTO;
import com.akata.offerservice.models.OfferModel;
import com.akata.offerservice.repository.OfferRepository;
import com.akata.offerservice.services.interfaces.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private OfferMapper offerMapper;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryService categoryService;

    @Override
    public OfferResponseDTO save(OfferModel offerModel) {
        /*
        * Need to get a client to another micro-service
        * */
        OfferRequestDTO offerRequestDTO = new OfferRequestDTO();
        offerRequestDTO.setClient(this.clientMapper.clientResponseDTOClient(this.clientService
                .getClient(offerModel.getClient())));
        offerRequestDTO.setApplicant_number(offerModel.getApplicant_number());
        offerRequestDTO.setDeadline(offerModel.getDeadline());
        offerRequestDTO.setDetails(offerModel.getDetails());
        offerRequestDTO.setPost_date(offerModel.getPost_date());
        offerRequestDTO.setTheme(offerModel.getTheme());
        offerRequestDTO.setBudget(offerModel.getBudget());
        offerRequestDTO.setCategory(this.categoryMapper.categoryResponseDTOCategory(this.categoryService
                .getCategory(offerModel.getCategory())));

        System.out.println("category: "+ offerRequestDTO.getCategory().getName()+" Id: "+offerRequestDTO.getCategory().getId());

        Offer offer = this.offerMapper.offerRequestDTOOffer(offerRequestDTO);
        System.out.println("Offer: "+offer.toString());

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
        return offers.stream().map(offer -> this.offerMapper.offerToOfferResponseDTO(offer)).collect(Collectors.toList());
    }

    @Override
    public List<OfferResponseDTO> getAllByClientId(Long id) {
        List<Offer> offers = this.offerRepository.findByClientID(id);
        return offers.stream().map((offer -> this.offerMapper.offerToOfferResponseDTO(offer))).collect(Collectors.toList());
    }
}
