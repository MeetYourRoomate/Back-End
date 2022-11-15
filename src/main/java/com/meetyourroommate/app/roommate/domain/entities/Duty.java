package com.meetyourroommate.app.roommate.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meetyourroommate.app.shared.domain.enumerate.Status;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Duty {
    @Id
    public Long id;

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

    @ManyToMany(mappedBy = "duties", fetch = FetchType.LAZY)
    public List<Roommate> roommateList;

    @Embedded
    public Audit audit = new Audit();

    public Duty() {
    }
}
