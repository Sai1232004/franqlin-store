package com.franqlinstore.franqlin_store.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="admins")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    public AdminEntity() {}

    public AdminEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
