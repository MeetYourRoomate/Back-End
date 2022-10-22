package com.meetyourroommate.app.rental.infrastructure.persistance.jpa;

import com.meetyourroommate.app.property.domain.aggregates.Property;
import com.meetyourroommate.app.rental.domain.entities.RentalOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalOfferingRepository extends JpaRepository<RentalOffering, Long> {
    Optional<RentalOffering> findByProperty(Property property);
}
