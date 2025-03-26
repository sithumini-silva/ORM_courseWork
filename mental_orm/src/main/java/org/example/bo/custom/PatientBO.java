package org.example.bo.custom;



import org.example.bo.SuperBO;
import org.example.entity.Patient;
import org.example.models.PatientDTO;

import java.util.List;

public interface PatientBO extends SuperBO {


    boolean save(PatientDTO patientDTO);
    //public boolean exsistCustomer(String id) throws SQLException, ClassNotFoundException ;

    public boolean update(PatientDTO patientDTO);
    public boolean delete(PatientDTO object);
    public PatientDTO get(String value);

    List<PatientDTO> getAll();

    public PatientDTO searchById(String patientId);
    public boolean savePatient(PatientDTO patientDTO);

    List<String> getIds();

    public List<PatientDTO> getAllPatients();

    String getCurrentId();
    Patient getPatientById(String patientId) throws Exception;
   
}
