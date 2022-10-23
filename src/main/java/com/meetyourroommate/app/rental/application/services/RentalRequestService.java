package com.meetyourroommate.app.rental.application.services;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.rental.domain.entities.RentalOffering;
import com.meetyourroommate.app.rental.domain.entities.RentalRequest;
import com.meetyourroommate.app.shared.application.services.CrudService;

import java.util.List;
import java.util.Optional;

public interface RentalRequestService extends CrudService<RentalRequest,Long> {
    Optional<RentalRequest> findByProfileAndOffer(Profile profile, RentalOffering rentalOffering);
    List<RentalRequest> findByRentalOffering(RentalOffering rentalOffering);
    List<RentalRequest> findByProfile(Profile profile);
}
