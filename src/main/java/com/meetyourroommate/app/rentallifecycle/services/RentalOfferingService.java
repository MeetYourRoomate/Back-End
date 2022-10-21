package com.meetyourroommate.app.rentallifecycle.services;

import com.meetyourroommate.app.rentallifecycle.domain.entities.RentalOffering;
import com.meetyourroommate.app.shared.application.services.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RentalOfferingService extends CrudService<RentalOffering, Long> {
    Page<RentalOffering> findByOffsetAndPageSize(int offset, int pagesize);
    Page<RentalOffering> findByOffsetAndPageSizeAndField(int offset, int pagesize, String field, String order);
}
