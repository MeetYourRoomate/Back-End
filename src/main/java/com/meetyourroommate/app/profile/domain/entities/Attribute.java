package com.meetyourroommate.app.profile.domain.entities;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
public class Attribute {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String value;
    private String type;
    @ManyToMany(mappedBy = "attributes", cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
    }, fetch = FetchType.LAZY)
    private Set<Profile> profiles = new HashSet<>();
    @Embedded
    private Audit audit = new Audit();
    public Attribute updateAudit(){
        audit.setUpdatedAt(new Date());
        return this;
    }
}
