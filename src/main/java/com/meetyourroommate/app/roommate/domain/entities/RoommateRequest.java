package com.meetyourroommate.app.roommate.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    @JoinColumn(name = "student_requestor_id", unique = true)
    public Profile studentRequestor;
    @ManyToOne
    @JoinColumn(name = "student_requested_id", unique = true)
    public Profile studentRequested;

    @Enumerated(EnumType.STRING)
    public Status status = Status.PENDING;

    @Embedded
    @JsonIgnore
    public Audit audit = new Audit();

    public RoommateRequest() {
    }
}