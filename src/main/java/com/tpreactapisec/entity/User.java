package com.tpreactapisec.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_name")
    private String username;

    @Column(name = "email")
    private String email;


    @Column(name = "password")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy="user",fetch= FetchType.EAGER)
    private Set<Authority> authorities;

}
