package org.example.dao.custom;


import org.example.dao.CrudDAO;
import org.example.entity.Register;
import org.hibernate.Session;

import java.util.List;

public interface PatientProgramDAO extends CrudDAO<Register> {
    String getCurrentId();


//    boolean savePayment(Payment payment, Session session);
    boolean save(Register register);

//    boolean saveRegistration(List<RegisterDTO> registerDTOList);
    public boolean saveRegistration(List<Register> registrationList, Session session);



}
