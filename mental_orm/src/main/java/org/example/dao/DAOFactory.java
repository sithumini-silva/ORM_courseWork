package org.example.dao;


import org.example.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)? daoFactory=new DAOFactory() : daoFactory;
    }
    public enum DAOTypes{
        PATIENT,PROGRAMME,USER,PAYMENT,PATIENTPROGRAM
    }
    public SuperDAO getDAO(DAOTypes types){
       switch (types){
           case  PATIENT:
               return  new PatientDAOImpl();
           case  PROGRAMME:
               return  new ProgramDAOImpl();
           case  USER:
               return  new UserDAOImpl();
           case  PAYMENT:
               return  new PaymentDAOImpl();
           case  PATIENTPROGRAM:
               return  new PatientProgramDAOImpl();
           default:
               return null;
       }
    }
}
