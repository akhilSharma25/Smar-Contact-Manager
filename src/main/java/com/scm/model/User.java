package com.scm.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
@Table(name = "users")
public class User {

    @Id
    private String userId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    private String password;
    @Column(length = 1000)
    private String about;
    @Column(length = 1000)
    private String profilePic;
    private String phoneNumber;
    private  Boolean enabled=false;
    private Boolean emailVerified=false;
    private Boolean phoneVerified=false;
    @Enumerated(EnumType.STRING)
    private Providers provider=Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch =FetchType.LAZY,orphanRemoval = true)
    private List<Contacts>contacts=new ArrayList<>();




}
