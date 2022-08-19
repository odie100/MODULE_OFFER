package com.akata.offerservice.dto;

import com.akata.offerservice.entities.Category;
import com.akata.offerservice.models.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class OfferResponseDTO {
    private Long offer_id;
    private String theme;
    private String details;
    private LocalDate deadline;
    private LocalDate post_date;
    private int applicant_number;
    private float budget;
    private Long client_id;
    private String status;

    Client client;
    Category category;
}
