package com.meetyourroommate.app.rental.application.services;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.property.domain.aggregates.Property;
import com.meetyourroommate.app.rental.domain.entities.RentalOffering;
import com.meetyourroommate.app.shared.application.services.CrudService;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface RentalOfferingService extends CrudService<RentalOffering, Long> {
    Page<RentalOffering> findByOffsetAndPageSize(int offset, int pagesize);
    Page<RentalOffering> findByOffsetAndPageSizeAndField(int offset, int pagesize, String field, String order);
    Optional<RentalOffering> findByProperty(Property property);
    List<RentalOffering> findAllByProperty_Profile(Profile profile);
}
