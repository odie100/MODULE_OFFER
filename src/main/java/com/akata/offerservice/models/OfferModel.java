package com.akata.offerservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class OfferModel {
    private String theme;
    private String details;
    private LocalDate deadline;
    private String type;
    private String technology;
    private LocalDate post_date;
    private int applicant_number;
    private Long client;
    private Long category;
    private Float budget;
    private String status;
}
