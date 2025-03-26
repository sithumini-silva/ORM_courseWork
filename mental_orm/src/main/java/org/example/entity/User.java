package org.example.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data

@Entity
public class User {
    @Id
    private String userId;
    private String name;
    private String role;
    private String tel;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Patient> patientList;

    public User(String userId) {
        this.userId=userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }



    public User(String userId, String name, String role, String tel, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.tel = tel;
        this.email = email;
        this.password = password;
    }

    public User() {
    }
}
