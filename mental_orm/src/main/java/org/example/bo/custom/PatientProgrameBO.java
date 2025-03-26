package org.example.bo.custom;



import org.example.bo.SuperBO;
import org.example.models.ProgramDTO;
import org.example.models.RegisterDTO;
import org.example.models.PatientDTO;

import java.util.List;

public interface PatientProgrameBO extends SuperBO {
    String getCurrentId();

    public PatientDTO searchPatient(String patientId);
    public List<RegisterDTO> getAllRegistrations();

    public boolean saveRegistration(RegisterDTO registrationDTO);

    public ProgramDTO searchProgram(String programId);

//    List<RegisterDTO> getAll();
List<RegisterDTO> getAll();

//    boolean save(CartTm cartTm);
//
//    void placeOfRegister(List<RegisterDTO> registrationDTOList);
//
//    boolean placeRegister(List<RegisterDTO> registerDTO);
//    void placeRegisters(List<RegisterDTO> registrationDTOList);
//
//    void saveRegisters(List<RegisterDTO> registrationDTOList);

}
