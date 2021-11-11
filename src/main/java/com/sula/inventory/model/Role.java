package com.sula.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ROLES", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "ID"
        })
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseModel{

    @Column(name = "NAME", length = 250, unique = true, nullable = false)
    private String name;
    @Column(length = 60)
    private String description;
}
