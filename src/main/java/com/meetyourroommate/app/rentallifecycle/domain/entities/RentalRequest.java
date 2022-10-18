package com.meetyourroommate.app.rentallifecycle.domain.entities;

import com.meetyourroommate.app.rentallifecycle.domain.valueobjects.RentalRequestId;
import com.meetyourroommate.app.shared.enumerate.Status;
import com.meetyourroommate.app.shared.valueobjects.Audit;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RentalRequest {
    @Id
    @Column(name = "id", unique = true, updatable = false)
    private RentalRequestId rentalRequestId;
    @Embedded
    private Audit audit;
    @Embedded
    private Status status;
}
