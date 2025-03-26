package org.example.dao.custom;



import org.example.dao.CrudDAO;
import org.example.entity.Patient;

import java.util.List;

public interface PatientDAO extends CrudDAO<Patient> {

   List<String> getIds();
   String getCurrentId();
   Patient getObject(String value);




}
