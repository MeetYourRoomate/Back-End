package com.meetyourroommate.app.rental.infrastructure.persistance.jpa;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.rental.domain.entities.RentalOffering;
import com.meetyourroommate.app.rental.domain.entities.RentalRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRequestRepository extends JpaRepository<RentalRequest, Long> {
    Optional<RentalRequest> findByStudentProfileAndRentalOffering(Profile profile, RentalOffering rentalOffering);
    List<RentalRequest> findAllByRentalOffering(RentalOffering rentalOffering);

    List<RentalRequest> findAllByStudentProfile(Profile profile);
    List<RentalRequest> findAllByRentalOffering_Property_Profile(Profile profile);

}
