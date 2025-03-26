package org.example.tdm;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data

public class PatientTm {
    private String id;
    private String user_id;
    private String name;
    private String address;
    private String email;
    private String tel;




    @Override
    public String toString() {
        return "PatientTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

    public PatientTm(String id, String name, String address, String email, String tel) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.tel = tel;
    }

    public PatientTm(String id, String user_id, String name, String address, String email, String tel) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.tel = tel;
    }
}
