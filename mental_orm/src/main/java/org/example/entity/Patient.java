package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Patient {
    @Id
    private String id;
    private String name;
    private String address;
    private String email;
    private String tel;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    List<Register> registerList;


    public Patient(String id, String name, String address, String email, String tel, User user) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.tel = tel;
        this.user = user;
    }

    public Patient(String id, String name, String address, String email, String tel) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.tel = tel;

    }


    public Patient(String id, User user, String name, String address, String tel, String email) {
        this.id = id;
        this.user=user;
        this.name=name;
        this.address = address;
        this.tel=tel;
        this.email=email;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", user=" + user +
                ", registerList=" + registerList +
                '}';
    }

    public Patient(String id) {
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Register> getRegisterList() {
        return registerList;
    }

    public void setRegisterList(List<Register> registerList) {
        this.registerList = registerList;
    }
}
