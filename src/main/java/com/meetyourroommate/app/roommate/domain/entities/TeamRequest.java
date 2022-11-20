package com.meetyourroommate.app.roommate.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.shared.domain.enumerate.Status;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class TeamRequest {
    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "student_requestor")
    private Profile StudentRequestor;

    @ManyToOne
    @JoinColumn(name = "team_requested")
    private Team teamRequested;

    @OneToMany(mappedBy = "teamRequest",cascade = CascadeType.REMOVE)
    private List<RoommateStatus> roommateStatuses = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @Embedded
    @JsonIgnore
    private Audit audit = new Audit();

    public TeamRequest(){

    }
    public void AppendRoommateStatus(RoommateStatus roommateStatus){
        this.roommateStatuses.add(roommateStatus);
    }
}
