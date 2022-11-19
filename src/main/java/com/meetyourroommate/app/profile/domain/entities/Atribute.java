package com.meetyourroommate.app.profile.domain.entities;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
public class Atribute {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String value;
    private String type;
    @ManyToMany(mappedBy = "atributes", cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
    }, fetch = FetchType.LAZY)
    private Set<Profile> profiles = new HashSet<>();
    @Embedded
    private Audit audit = new Audit();
    public Atribute updateAudit(){
        audit.setUpdatedAt(new Date());
        return this;
    }
}
