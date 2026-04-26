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
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    private String password;
    private String address;
    @Column(length = 1000)
    private String description;
    @Column(length = 1000)
    private String picture;
//    private List<String>socialLinks=new ArrayList<>();
    private Boolean favorite=false;
//    private  String websiteLink;
//    private  String linkedLink;


    @ManyToOne
    private User user;


    @OneToMany(mappedBy = "contacts",cascade = CascadeType.ALL,fetch =FetchType.EAGER,orphanRemoval = true)
    private List<SocialLink>links=new ArrayList<>();
}
