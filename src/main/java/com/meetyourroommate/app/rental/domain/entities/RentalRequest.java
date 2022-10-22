package com.meetyourroommate.app.rental.domain.entities;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.rental.domain.aggregates.Rental;
import com.meetyourroommate.app.rental.domain.valueobjects.RentalRequestId;
import com.meetyourroommate.app.shared.domain.enumerate.Status;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RentalRequest {
    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private Audit audit;
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;
    private String message;

    @ManyToOne
    @JoinColumn(name = "student_profile_id")
    private Profile studentProfile;
    @ManyToOne
    @JoinColumn(name = "rental_offert_id")
    private RentalOffering rentalOffering;

}
