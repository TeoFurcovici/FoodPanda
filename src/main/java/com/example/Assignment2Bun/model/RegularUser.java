package com.example.Assignment2Bun.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class RegularUser extends User {
    public RegularUser( String firstName, String lastName, String email, String password,String username,String isAdmin) {
        super( firstName, lastName, email, password,username,isAdmin);
    }

    public RegularUser() {

    }



}
