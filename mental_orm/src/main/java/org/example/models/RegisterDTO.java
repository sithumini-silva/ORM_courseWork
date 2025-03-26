package org.example.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Program;
import org.example.entity.Patient;


@NoArgsConstructor
@Data

public class RegisterDTO {
    private String register_id;
    private String date;
    private Patient patient;
    private Program program;
    private String patientName;
    private String programName;
    private double programFee;
    private double regiFee;

    public RegisterDTO(String register_id, String date, Patient patient, Program program, String patientName, String programName, double programFee, double regiFee) {
        this.register_id = register_id;
        this.date = date;
        this.patient = patient;
        this.program = program;
        this.patientName = patientName;
        this.programName = programName;
        this.programFee = programFee;
        this.regiFee = regiFee;
    }

    public RegisterDTO(String register_id) {
        this.register_id = register_id;
    }

    public RegisterDTO(String registerId, String date, String patientName, String programName, double programFee, double regiFee) {
        this.register_id=registerId;
        this.date=date;
        this.patientName=patientName;
        this.programName=programName;
        this.programFee=programFee;
        this.regiFee=regiFee;
    }


}
