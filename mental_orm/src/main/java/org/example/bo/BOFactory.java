package org.example.bo;


import org.example.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;

    }
    public enum BOTypes{
        PATIENT,PROGRAMME,USER,PAYMENT,PATIENTPROGRAME
    }
    public SuperBO getBO(BOTypes types){
        switch (types){
            case PATIENT:
                return  new PatientBOImpl();
            case PROGRAMME:
                return  new ProgrammeBOImpl();
            case USER:
                return  new UserBOImpl();
            case PAYMENT:
                return  new PaymentBOIml();
            case PATIENTPROGRAME:
                return  new PatientProgrammeBOIml();
            default:
                return null;
        }
    }
}
