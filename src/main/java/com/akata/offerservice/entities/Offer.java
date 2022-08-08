package com.akata.offerservice.entities;

import com.akata.offerservice.models.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long offer_id;
    private String theme;
    private String details;
    private LocalDate deadline;
    private LocalDate post_date;
    private int applicant_number;

    private Float budget;
    private Long client_id;

    @Transient
    Client client;

    @ManyToOne
    Category category;
}
