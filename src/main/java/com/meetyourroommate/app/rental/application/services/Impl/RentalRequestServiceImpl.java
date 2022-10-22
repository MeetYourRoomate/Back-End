package com.meetyourroommate.app.rental.application.services.Impl;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.rental.application.services.RentalRequestService;
import com.meetyourroommate.app.rental.domain.entities.RentalOffering;
import com.meetyourroommate.app.rental.domain.entities.RentalRequest;
import com.meetyourroommate.app.rental.infrastructure.persistance.jpa.RentalRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalRequestServiceImpl implements RentalRequestService {
    @Autowired
    private RentalRequestRepository rentalRequestRepository;
    @Override
    public RentalRequest save(RentalRequest rentalRequest) throws Exception {
        return rentalRequestRepository.save(rentalRequest);
    }

    @Override
    public List<RentalRequest> findAll() throws Exception {
        return rentalRequestRepository.findAll();
    }

    @Override
    public Optional<RentalRequest> findById(Long id) throws Exception {
        return rentalRequestRepository.findById(id);
    }

    @Override
    public RentalRequest update(Long aLong, RentalRequest rentalRequest) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        rentalRequestRepository.deleteById(id);
    }

    @Override
    public Optional<RentalRequest> findByProfileAndOffer(Profile profile, RentalOffering rentalOffering) {
        return rentalRequestRepository.findByStudentProfileAndRentalOffering(profile,rentalOffering);
    }

    @Override
    public List<RentalRequest> findByRentalOffering(RentalOffering rentalOffering) {
        return rentalRequestRepository.findAllByRentalOffering(rentalOffering);
    }

    @Override
    public List<RentalRequest> findByProfile(Profile profile) {
       return rentalRequestRepository.findAllByStudentProfile(profile);
    }
}
