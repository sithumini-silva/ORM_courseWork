package org.example.bo.custom.impl;


import org.example.bo.custom.PatientProgrameBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.PatientProgramDAO;
import org.example.dao.custom.PaymentDAO;
import org.example.dao.custom.ProgramDAO;
import org.example.dao.custom.PatientDAO;
import org.example.dao.custom.impl.PaymentDAOImpl;
import org.example.dao.custom.impl.PatientProgramDAOImpl;
import org.example.entity.Program;
import org.example.entity.Register;
import org.example.entity.Patient;
import org.example.models.ProgramDTO;
import org.example.models.RegisterDTO;
import org.example.models.PatientDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PatientProgrammeBOIml implements PatientProgrameBO {
    PatientProgramDAO patientProgramDAO = (PatientProgramDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PATIENTPROGRAM);
    PatientDAO patientDAO = (PatientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PATIENT);
    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROGRAMME);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    private PatientProgramDAO patientProgramDAO1;
    private PaymentDAO paymentDAO1;

    public PatientProgrammeBOIml() {
        // Initialize DAOs (normally injected via a service or factory)
        this.patientProgramDAO = new PatientProgramDAOImpl();
        this.paymentDAO1 = new PaymentDAOImpl();
    }

//    @Override
//    public List<RegisterDTO> getAll() {
//        /*List<RegisterDTO> registerDTOS = new ArrayList<>();
//        List<Register> all = patientProgramDAO.getAll();
//        for (Register register : all){
//            registerDTOS.add(new RegisterDTO(register.getRegister_id(),register.getDate(),register.getPatiententName(),register.getProgramName(),register.getProgramFee(),register.getRegiFee()));
//        }
//        return registerDTOS;*/
//        List<RegisterDTO> regList = new ArrayList<>();
//        List<Register> registrations = patientProgramDAO.getAll();
////
////
////        for (Register registration : registrations){
////
////            Program programDTO = new Program(registration.getProgram().getCode());
////            Patient patientDTO = new Patient(registration.getPatient().getId());
////
////          /*  System.out.println(programDTO);
////*/
////            RegisterDTO registrationDTO = new RegisterDTO(registration.getRegister_id(),registration.getDate(),patientDTO,programDTO,registration.getPatientName(),registration.getProgramName(),registration.getProgramFee(),registration.getRegiFee());
////            regList.add(registrationDTO);
////        }
////        return regList;
////    }
//
//    }

    @Override
    public List<RegisterDTO> getAll() {
        List<RegisterDTO> registerDTOList = new ArrayList<>();
        List<Register> registerList = patientProgramDAO.getAll(); // Fetch data from DAO

        // Map Register entities to RegisterDTO
        for (Register register : registerList) {
            RegisterDTO registerDTO = new RegisterDTO(
                    register.getRegister_id(),
                    register.getDate(),
                    register.getPatient(),
                    register.getProgram(),
                    register.getPatientName(),
                    register.getProgramName(),
                    register.getProgramFee(),
                    register.getRegiFee()
            );
            registerDTOList.add(registerDTO);
        }

        return registerDTOList; // Return DTO list
    }


    private String generateRegisterId() {
        return UUID.randomUUID().toString();  // Example of generating a UUID
    }

    @Override
    public PatientDTO searchPatient(String patientId) {
        Patient patient = patientDAO.search(patientId);
        return new PatientDTO(patient.getId(),patient.getUser(),patient.getName(),patient.getAddress(),patient.getEmail(),patient.getTel());
    }

    @Override
    public ProgramDTO searchProgram(String programId) {
        Program program = programDAO.search(programId);

        return new ProgramDTO(program.getCode(),program.getName(),program.getPrice(),program.getDuration());
    }


    @Override
    public boolean saveRegistration(RegisterDTO registrationDTO) {

        Program programDTO =registrationDTO.getProgram();
        Program program = new Program(programDTO.getCode());


        Patient patientDto = registrationDTO.getPatient();
        Patient patient = new Patient(patientDto.getId());

        return patientProgramDAO.save(new Register(registrationDTO.getRegister_id(),registrationDTO.getDate(),patient,program,registrationDTO.getPatientName(),registrationDTO.getProgramName(),registrationDTO.getProgramFee(),registrationDTO.getRegiFee()));
    }
    @Override
    public List<RegisterDTO> getAllRegistrations() {
                return null;
    }
    @Override
    public String getCurrentId() {
        return patientProgramDAO.getCurrentId();
    }














}


