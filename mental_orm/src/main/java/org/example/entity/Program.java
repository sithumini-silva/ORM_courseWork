package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@Data
@Entity
public class Program {
    @Id
    private String code;
    private String name;
    private double price;
    private int duration;

    @OneToMany(mappedBy = "program",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Register> registerList;


    public Program(String code) {
        this.code =code;
    }

    public Program(String code, String name, double price, int duration) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

}
