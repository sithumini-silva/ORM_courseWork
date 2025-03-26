package org.example.tdm;

public class CartTm {
    private String registerId;
    private String patientId;
    private String programId;
    private double regiFee;
    private double programFee;
    private String date;
    private String paymentId; // Add this field to hold the payment ID

    // Constructor
    public CartTm(String registerId, String patientId, String programId, double regiFee, double programFee, String date, String paymentId) {
        this.registerId = registerId;
        this.patientId = patientId;
        this.programId = programId;
        this.regiFee = regiFee;
        this.programFee = programFee;
        this.date = date;
        this.paymentId = paymentId; // Initialize the payment ID
    }

    public CartTm(String registerId, String patientId, String programId, double regiFee, double programFee, String date) {
        this.registerId = registerId;
        this.patientId = patientId;
        this.programId = programId;
        this.regiFee = regiFee;
        this.programFee = programFee;
        this.date = date;
    }


    // Getters and Setters
    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public double getRegiFee() {
        return regiFee;
    }

    public void setRegiFee(double regiFee) {
        this.regiFee = regiFee;
    }

    public double getProgramFee() {
        return programFee;
    }

    public void setProgramFee(double programFee) {
        this.programFee = programFee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
