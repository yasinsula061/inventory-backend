package com.sula.inventory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "TBL_USER_ROLE", indexes = {
        @Index(name = "IDX_UR_USER_ROLE", columnList = "USER_ID, ROLE_ID")}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRole extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User user;

    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Role role;

}