package com.meetyourroommate.app.rental.infrastructure.persistance.jpa;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.property.domain.aggregates.Property;
import com.meetyourroommate.app.rental.domain.entities.RentalOffering;
import com.meetyourroommate.app.rental.domain.enumerate.Visibility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalOfferingRepository extends JpaRepository<RentalOffering, Long> {
    Optional<RentalOffering> findByProperty(Property property);
    List<RentalOffering> findAllByProperty_Profile(Profile profile);
    Page<RentalOffering> findAllByVisibility(Visibility visibility,Pageable pageable);
    List<RentalOffering> findAllByVisibility(Visibility visibility);
}
