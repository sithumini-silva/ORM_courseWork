package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Register {

    @Id
    private String register_id;
    private String date;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    private String patientName;
    private String programName;
    private double programFee;
    private double regiFee;


    public Register(String register_id, String date, Patient patient, Program program, String patientName, String programName, double programFee, double regiFee) {
        this.register_id = register_id;
        this.date = date;
        this.patient = patient;
        this.program = program;
        this.patientName = patientName;
        this.programName = programName;
        this.programFee = programFee;
        this.regiFee = regiFee;
    }

    public Register(String register_id, String date, String patientName, String programName, double programFee, double regiFee) {
        this.register_id = register_id;
        this.date = date;
        this.patientName = patientName;
        this.programName = programName;
        this.programFee = programFee;
        this.regiFee = regiFee;
    }

    public Register(String register_id) {
        this.register_id = register_id;


    }




    @Override
    public String toString() {
        return "Register{" +
                "register_id='" + register_id + '\'' +
                ", date='" + date + '\'' +
                ", patientName='" + patientName + '\'' +
                ", programName='" + programName + '\'' +
                ", programFee=" + programFee +
                ", regiFee=" + regiFee +
                '}';
    }
}
