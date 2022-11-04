package com.meetyourroommate.app.rental.application.internal.events;

import com.meetyourroommate.app.profile.application.services.ProfileService;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.rental.application.services.RentalOfferingService;
import com.meetyourroommate.app.rental.application.services.RentalRequestService;
import com.meetyourroommate.app.rental.domain.entities.RentalOffering;
import com.meetyourroommate.app.rental.domain.entities.RentalRequest;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RentalRequestEventHandler {

    private RentalRequestService rentalRequestService;
    private ProfileService profileService;
    private RentalOfferingService rentalOfferingService;

    public RentalRequestEventHandler(RentalRequestService rentalRequestService, ProfileService profileService, RentalOfferingService rentalOfferingService) {
        this.rentalRequestService = rentalRequestService;
        this.profileService = profileService;
        this.rentalOfferingService = rentalOfferingService;
    }

    @EventHandler
    public void on(CreateRentalRequestEvent createRentalRequestEvent) throws Exception{
        Optional<Profile> profile = profileService
                .findByUserId(createRentalRequestEvent.getUserId());
        if(profile.isEmpty()){
            throw new Exception("User not found.");
        }
        Optional<RentalOffering> rentalOffering = rentalOfferingService
                .findById(createRentalRequestEvent.getOfferId());

        if(rentalOffering.isEmpty()){
            throw new Exception("Offer not found.");
        }
        Optional<RentalRequest> optionalRentalRequest = rentalRequestService
                .findByProfileAndOffer(profile.get(), rentalOffering.get());

        if(optionalRentalRequest.isPresent()){
            throw new Exception("There is already a rental request");
        }
        RentalRequest rentalRequest = new RentalRequest(
                profile.get(),
                rentalOffering.get(),
                createRentalRequestEvent.getMessage());

        rentalRequestService.save(rentalRequest);
    }
}
