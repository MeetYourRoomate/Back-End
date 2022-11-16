package com.meetyourroommate.app.roommate.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meetyourroommate.app.shared.domain.enumerate.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class RoommateStatus {

    @Id
    private String id = UUID.randomUUID().toString();
    @ManyToOne
    @JoinColumn(name = "team_request_id")
    @JsonIgnore
    private TeamRequest teamRequest;

    @OneToOne
    @JoinColumn(name = "roommate_id")
    private Roommate roommate;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;
}
