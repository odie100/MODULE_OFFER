package com.akata.offerservice.repository;

import com.akata.offerservice.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query("SELECT o FROM Offer o where o.client_id = ?1")
    List<Offer> findByClientID(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Offer o set o.status = 'confirmed' where o.offer_id = ?1")
    int updateStatus(Long id);

}
