package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Payment {
    @Id
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "register_id")
    private Register register;
    private double fee;
    private double registerFee;
    private double totalFee;


    public Payment(String id,double fee, double registerFee, double totalFee) {
        this.id = id;
        this.fee = fee;
        this.registerFee = registerFee;
        this.totalFee = totalFee;
    }

    public Payment(Register register) {
        this.register = register;
    }

    public Payment(String id, double fee, double registerFee, double totalFee, Register register) {
        this.id=id;
        this.fee = fee;
        this.registerFee = registerFee;
        this.totalFee =totalFee;
        this.register =register;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", fee=" + fee +
                ", registerFee=" + registerFee +
                ", totalFee=" + totalFee +
                '}';
    }
}