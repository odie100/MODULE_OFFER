package com.akata.offerservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Client {
    private Long id;
    private String username;
    private Location location;
    private String name;
    private String type;
    private String description;
    private LocalDate creation;
}
