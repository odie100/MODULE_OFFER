package com.akata.offerservice.dto;

import com.akata.offerservice.entities.Category;
import com.akata.offerservice.models.Client;

import java.time.LocalDate;

public class OfferRequestDTO {
    private String theme;
    private String details;
    private LocalDate deadline;
    private LocalDate post_date;
    private int applicant_number;
    private float budget;
    private Long client_id;
    /*Client client;*/
    Category category;
}
