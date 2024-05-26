package com.app.vb.blogapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    //one account(think as user) can post many posts
    @OneToMany(mappedBy = "account")
    private List<Post> posts;

    //relationship between account and authority:
    //each user can have multiple authorities attach to it
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_authority",
    joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    private Set<Authority> authorities = new HashSet<>();

    //just to see in debug time
    @Override
    public String toString() {
        return "Account{" +
                "email: " + email +
                ", password: " + password +
                ", firstName: " + firstName +
                ", lastName: " + lastName;
    }
}
