package com.meetyourroommate.app.roommate.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
public class Roommate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    @JoinColumn(name = "team_id", unique = true)
    public Team team;

    @OneToOne
    @JoinColumn(name = "profile_id", unique = true)
    public Profile profile;

    @Embedded
    @JsonIgnore
    public Audit audit = new Audit();

    public Roommate() {
    }
}
