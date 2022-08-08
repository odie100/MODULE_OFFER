package com.akata.offerservice.repository;

import com.akata.offerservice.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query("SELECT o FROM Offer o where o.client.id = ?1")
    List<Offer> findByClientID(Long id);
}
