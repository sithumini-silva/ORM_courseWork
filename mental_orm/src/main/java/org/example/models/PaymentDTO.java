package org.example.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class PaymentDTO {

    private String id;
    RegisterDTO register;
    private double fee;
    private double registerFee;
    private double totalFee;



    public PaymentDTO(String id,  double fee, double registerFee, double totalFee) {
        this.id = id;
        this.fee = fee;
        this.registerFee = registerFee;
        this.totalFee = totalFee;
    }

    public PaymentDTO(String id, double fee, double registerFee) {
        this.id = id;
        this.fee = fee;
        this.registerFee = registerFee;
    }

    public PaymentDTO(String paymetId, double fee, double total, double regi, RegisterDTO register) {
        this.id =paymetId;
        this.fee=fee;
        this.totalFee=total;
        this.registerFee=regi;
        this.register=register;

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
