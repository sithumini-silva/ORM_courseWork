package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.PatientProgramDAO;
import org.example.entity.Payment;
import org.example.entity.Register;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;

public class PatientProgramDAOImpl implements PatientProgramDAO {



    @Override
    public boolean save(Register registration) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Object isSaved = session.save(registration);

        if(isSaved != null){
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }



    @Override
    public boolean update(Register object) {
        return false;
    }

    @Override
    public boolean delete(Register object) {
        return false;
    }

    @Override
    public Register get(Register object) {
        return null;
    }

//    @Override
//    public List<Register> getAll() {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//
//        Query query = session.createQuery("from Register");
//        List<Register> registrations = query.list();
//        transaction.commit();
//        session.close();
//        return registrations;
//    }

    @Override
    public List<Register> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Register");
        List<Register> registrations = query.list();
        transaction.commit();
        session.close();
        return registrations;
    }


    @Override
    public Register search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Register where register_id =?1");
        query.setParameter(1, id);
        Register registration = (Register) query.uniqueResult();
        transaction.commit();
        return registration;
    }

    @Override
    public String getCurrentId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select id from Register order by id desc limit 1");
        String id = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        return id;
    }





    public boolean savePayment(Payment payment, Session session) {
        try {
            session.save(payment);  // Hibernate saves the payment entity
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




    public String generateRegisterId() {
        return UUID.randomUUID().toString();
    }


    public boolean saveRegistration(List<Register> registrationList, Session session) {
        Transaction transaction = null;
        try {

            transaction = session.beginTransaction();


            session.save(registrationList);


            transaction.commit();
            return true;
        } catch (HibernateException e) {

            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }




}
