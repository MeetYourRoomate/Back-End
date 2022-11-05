package com.meetyourroommate.app.roommate.domain.entities;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.shared.domain.enumerate.Status;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
public class RoommateRequest {
    @Id
    public Long id;

    @OneToOne
    @JoinColumn(name = "student_id", unique = true)
    public Profile student;

    @Enumerated(EnumType.STRING)
    public Status status = Status.PENDING;

    @Embedded
    public Audit audit = new Audit();

    public RoommateRequest() {
    }
}