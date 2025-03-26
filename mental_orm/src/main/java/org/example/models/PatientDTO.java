package org.example.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientDTO {
    private String id;
    private User user;
    private String name;
    private String address;
    private String email;
    private String tel;

    public PatientDTO(String id, User user, String name) {
        this.id = id;
        this.user = user;
        this.name = name;
    }

    public PatientDTO(String id, String name, String address, String email, String tel) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.tel = tel;
    }

    public PatientDTO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
