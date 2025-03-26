package org.example.bo.custom.impl;


import org.example.bo.custom.PatientBO;
import org.example.config.FactoryConfiguration;
import org.example.dao.DAOFactory;
import org.example.dao.custom.PatientDAO;
import org.example.entity.Patient;
import org.example.entity.User;
import org.example.models.PatientDTO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PatientBOImpl implements PatientBO {
   // private static CustomerDAOImpl customerDAOImpl;
   PatientDAO patientDAO = (PatientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PATIENT);

    public boolean save(PatientDTO patientDTO) {
        User user =patientDTO.getUser();

        User user1 = new User(user.getUserId());

        return patientDAO.save(new Patient(patientDTO.getId(), user1, patientDTO.getName(), patientDTO.getAddress(), patientDTO.getEmail(), patientDTO.getTel()));
    }

    @Override
    public boolean update(PatientDTO patientDTO) {
        return patientDAO.update( new Patient(patientDTO.getId(), patientDTO.getUser(), patientDTO.getName(),patientDTO.getAddress(),patientDTO.getTel(),patientDTO.getEmail()));
    }

    @Override
    public boolean delete(PatientDTO patientDTO) {
        return patientDAO.delete(new Patient(patientDTO.getId(), patientDTO.getUser(), patientDTO.getName(),patientDTO.getAddress(),patientDTO.getTel(),patientDTO.getEmail()));
    }

    @Override
    public PatientDTO get(String value) {
        Patient object = patientDAO.getObject(value);
        return new PatientDTO(object.getId(),object.getName(),object.getAddress(),object.getTel(),object.getEmail());
    }

    @Override
    public List<PatientDTO> getAll() {
        List<PatientDTO> patientDTOS = new ArrayList<>();
        List<Patient> all = patientDAO.getAll();
        for (Patient patient : all){
            patientDTOS.add(new PatientDTO(patient.getId(),patient.getName(),patient.getAddress(),patient.getTel(),patient.getEmail()));
        }
        return patientDTOS;
    }
    @Override
    public List<String> getIds() {
        return patientDAO.getIds();
    }

    @Override
    public String getCurrentId() {
        return patientDAO.getCurrentId();
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientDAO.getAll();
        List<PatientDTO> stList = new ArrayList<>();

        for (Patient patient : patients){
            PatientDTO patientDTO = new PatientDTO(patient.getId(),patient.getUser(),patient.getName(),patient.getAddress(),patient.getEmail(),patient.getTel());
            stList.add(patientDTO);
        }
        return stList;

    }
    @Override
    public Patient getPatientById(String patientId) throws Exception {
        Session session = null;
        try {
            // Open a session
            session = FactoryConfiguration.getInstance().getSession();

            // Create a query to fetch the patient by ID
            String hql = "FROM Patient WHERE id = :patientId";
            Query<Patient> query = session.createQuery(hql, Patient.class);
            query.setParameter("patientId", patientId);

            // Get the result (Single patient)
            Patient patient = query.uniqueResult();

            return patient;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error retrieving patient by ID.");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public PatientDTO searchById(String patientId) {
        Patient patient = patientDAO.search(patientId);
        return new PatientDTO(patient.getId(),patient.getUser(),patient.getName(),patient.getAddress(),patient.getEmail(),patient.getTel());
    }

    @Override
    public boolean savePatient(PatientDTO patientDTO) {
        return patientDAO.save(new Patient(patientDTO.getId(),patientDTO.getUser(),patientDTO.getName(),patientDTO.getAddress(),patientDTO.getEmail(),patientDTO.getTel()));
    }
}
