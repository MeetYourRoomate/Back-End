package com.meetyourroommate.app.roommate.domain.entities;

import com.meetyourroommate.app.shared.domain.enumerate.Status;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
public class Duty {
    @Id
    public Long id;
    @Lob
    public String description;
    @Enumerated(EnumType.STRING)
    public Status status = Status.PENDING;
    public Date dateline;
    @Embedded
    public Audit audit = new Audit();

    public Duty() {
    }
}
