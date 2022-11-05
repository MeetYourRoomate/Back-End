package com.meetyourroommate.app.roommate.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Team {
    @Id
    public Long id;
    public String name;

    @OneToMany(mappedBy = "team")
    public List<Roommate> roommates;

    @Embedded
    @JsonIgnore
    public Audit audit = new Audit();

    public Team() {
    }
}
