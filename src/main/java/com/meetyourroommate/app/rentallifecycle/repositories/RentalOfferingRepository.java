package com.meetyourroommate.app.rentallifecycle.repositories;

import com.meetyourroommate.app.rentallifecycle.domain.entities.RentalOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalOfferingRepository extends JpaRepository<RentalOffering, Long> {
}
