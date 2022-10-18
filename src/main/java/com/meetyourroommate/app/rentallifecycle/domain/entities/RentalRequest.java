package com.meetyourroommate.app.rentallifecycle.domain.entities;

import com.meetyourroommate.app.rentallifecycle.domain.valueobjects.Status;
import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class RentalRequest {
    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private Status status;
}
