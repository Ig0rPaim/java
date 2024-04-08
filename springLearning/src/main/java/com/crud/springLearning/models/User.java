package com.crud.springLearning.models;

import java.util.ArrayList;
import java.util.List;

import com.crud.springLearning.DTOs.RoleDTO;
import com.crud.springLearning.DTOs.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String login;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "users_id"),
        inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private List<Role> roles= new ArrayList<Role>();
    

    public User(Long id, String name, String login, String password, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public User(UserDTO userDTO){
        this.id = userDTO.id();
        this.login = userDTO.login();
        this.name = userDTO.name();
        this.password = userDTO.password();
        this.roles = userDTO.roles().stream().map(Role::new).toList();
    }

    public User() {
    }

    
}