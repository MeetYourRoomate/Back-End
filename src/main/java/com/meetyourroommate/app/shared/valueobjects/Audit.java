package com.meetyourroommate.app.shared.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Embeddable
public class Audit implements Serializable {
    public Audit(){
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
