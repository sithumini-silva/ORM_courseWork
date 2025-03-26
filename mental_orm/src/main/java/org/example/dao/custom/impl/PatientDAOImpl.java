package org.example.dao.custom.impl;


import org.example.config.FactoryConfiguration;
import org.example.dao.custom.PatientDAO;
import org.example.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class PatientDAOImpl implements PatientDAO {

    @Override
    public boolean save(Patient patient) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Object isSaved = session.save(patient);

        if(isSaved != null){
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }
    @Override
    public boolean update(Patient object) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Patient object) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Patient get(Patient object) {
        return null;
    }

    @Override
    public List<Patient> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createNativeQuery("SELECT * FROM Patient");
        query.addEntity(Patient.class);
        List<Patient> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public List<String> getIds() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" select id from Patient");
        List<String> list = query.getResultList();
        transaction.commit();
        session.close();
        return list;
    }



    @Override
    public String getCurrentId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select id from Patient order by id desc limit 1");
        String id = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        return id;
    }



    @Override
    public Patient getObject(String value) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Patient where id = ?1");
        query.setParameter(1,value);
        Patient patient= (Patient) query.uniqueResult();
        transaction.commit();
        session.close();
        return patient;
    }


    public Patient getPatientById(String patientId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        Query query = session.createQuery("from Patient where id = :id");
        query.setParameter("id", patientId);


        Patient patient = (Patient) query.uniqueResult();

        transaction.commit();
        session.close();

        return patient;
    }

    @Override
    public Patient search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Patient where id =?1");
        query.setParameter(1, id);
        Patient patient = (Patient) query.uniqueResult();
        transaction.commit();
        return patient;
    }


}
