package org.example.tdm;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RegisterTm {
    private String registerId;
    private String date;
    private String patientId; // Changed from patient object to String
    private String programId; // Changed from Program object to String
    private String patientName;
    private String programName;
    private double programFee;
    private double regiFee;

    public RegisterTm(String registerId, String date, String patientId, String programId,
                      String patientName, String programName, double programFee, double regiFee) {
        this.registerId = registerId;
        this.date = date;
        this.patientId = patientId; // Assign patient ID
        this.programId = programId; // Assign program ID
        this.patientName = patientName;
        this.programName = programName;
        this.programFee = programFee;
        this.regiFee = regiFee;
    }

}
