package com.sula.inventory.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;



@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseModel implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 12)
    @Column(name = "ACTIVE", nullable = true)
    private boolean isActive = true;

    @Size(max = 12)
    @Column(name = "DELETED", nullable = true)
    private boolean isDeleted = false;

    @Size(max = 12)
    @Column(name = "LOCKED", nullable = true)
    private boolean isLocked = false;


    @Size(max = 120)
    @Column(name = "DATA_CHANGE_CREATED_BY", nullable = true)
    private String dataChangeCreatedBy;

    @Size(max = 120)
    @Column(name = "DATA_CHANGE_CREATED_TIME", nullable = true)
    private Date dataChangeCreatedTime;

    @Size(max = 120)
    @Column(name = "DATA_CHANGE_MODIFIED_BY")
    private String dataChangeLastModifiedBy;

    @Size(max = 120)
    @Column(name = "DATA_CHANGE_MODIFIED_TIME")
    private Date dataChangeLastModifiedTime;

    @PrePersist
    protected void prePersist() {
        if (this.dataChangeCreatedTime == null) dataChangeCreatedTime = new Date();
        if (this.dataChangeLastModifiedTime == null) dataChangeLastModifiedTime = new Date();
        if (this.dataChangeCreatedBy == null) {

            dataChangeCreatedBy ="Manuel Create Users";

        }
    }
    @PreUpdate
    protected void preUpdate() {
        this.dataChangeLastModifiedTime = new Date();
    }

    @PreRemove
    protected void preRemove() {
        this.dataChangeLastModifiedTime = new Date();
    }
}
