package com.meetyourroommate.app.rentallifecycle.services.Impl;

import com.meetyourroommate.app.rentallifecycle.domain.entities.RentalOffering;
import com.meetyourroommate.app.rentallifecycle.repositories.RentalOfferingRepository;
import com.meetyourroommate.app.rentallifecycle.services.RentalOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalOfferingServiceImpl implements RentalOfferingService {

    @Autowired
    private RentalOfferingRepository rentalOfferingRepository;
    @Override
    public RentalOffering save(RentalOffering rentalOffering) throws Exception {
        return rentalOfferingRepository.save(rentalOffering);
    }

    @Override
    public List<RentalOffering> findAll() throws Exception {
        return rentalOfferingRepository.findAll();
    }

    @Override
    public Optional<RentalOffering> findById(Long id) throws Exception {
        return rentalOfferingRepository.findById(id);
    }

    @Override
    public RentalOffering update(Long aLong, RentalOffering rentalOffering) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        rentalOfferingRepository.deleteById(id);
    }

    @Override
    public Page<RentalOffering> findByOffsetAndPageSize(int offset, int pagesize) {
        return rentalOfferingRepository.findAll(PageRequest.of(offset, pagesize));
    }

    @Override
    public Page<RentalOffering> findByOffsetAndPageSizeAndField(int offset, int pagesize, String field, String order) {
        switch (order){
            case "DESC":
                return rentalOfferingRepository.findAll(PageRequest.of(offset, pagesize).withSort(Sort.by(field).descending()));
            case "ASCE":
                return rentalOfferingRepository.findAll(PageRequest.of(offset, pagesize).withSort(Sort.by(field).ascending()));
            default:
                return rentalOfferingRepository.findAll(PageRequest.of(offset, pagesize).withSort(Sort.by(field)));
        }
    }
}
