package com.meetyourroommate.app.roommate.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meetyourroommate.app.shared.domain.enumerate.Status;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
public class Duty {
    @Id
    public String id = UUID.randomUUID().toString();

    public String title;
    @Lob
    public String description;

    @Enumerated(EnumType.STRING)
    public Status status = Status.PENDING;

    public Date dateline;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false )
    @JsonIgnore
    public Team team;

    @ManyToMany(fetch = FetchType.LAZY)
    public List<Roommate> roommateList = new ArrayList<>();

    @Embedded
    public Audit audit = new Audit();

    public Duty() {
    }
}
