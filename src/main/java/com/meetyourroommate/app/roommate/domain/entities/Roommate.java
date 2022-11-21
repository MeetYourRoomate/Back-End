package com.meetyourroommate.app.roommate.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Roommate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonIgnore
    private Team team;

    @ManyToMany(mappedBy = "roommateList",fetch = FetchType.LAZY)
    @JsonIgnore
    public List<Duty> duties;

    @OneToOne
    @JoinColumn(name = "profile_id", unique = true)
    private Profile profile;

    @Embedded
    @JsonIgnore
    private Audit audit = new Audit();

    public Roommate() {
    }
}
