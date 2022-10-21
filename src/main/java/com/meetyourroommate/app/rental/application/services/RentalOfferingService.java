package com.meetyourroommate.app.rental.application.services;

import com.meetyourroommate.app.rental.domain.entities.RentalOffering;
import com.meetyourroommate.app.shared.application.services.CrudService;
import org.springframework.data.domain.Page;


public interface RentalOfferingService extends CrudService<RentalOffering, Long> {
    Page<RentalOffering> findByOffsetAndPageSize(int offset, int pagesize);
    Page<RentalOffering> findByOffsetAndPageSizeAndField(int offset, int pagesize, String field, String order);
}
