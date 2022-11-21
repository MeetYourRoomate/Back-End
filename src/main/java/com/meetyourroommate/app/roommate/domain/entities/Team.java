package com.meetyourroommate.app.roommate.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Roommate> roommates;

    @OneToMany(mappedBy = "team",fetch = FetchType.LAZY)
    private List<Duty> duties;

    @Embedded
    @JsonIgnore
    private Audit audit = new Audit();

    public Team() {
    }
}
