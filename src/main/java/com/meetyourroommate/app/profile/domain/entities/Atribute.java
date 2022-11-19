package com.meetyourroommate.app.profile.domain.entities;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Atribute {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String value;
    private String type;
    @ManyToMany(mappedBy = "atributes", fetch = FetchType.LAZY)
    private List<Profile> profile;
    @Embedded
    private Audit audit;
    public Atribute updateAudit(){
        audit.setUpdatedAt(new Date());
        return this;
    }
}
