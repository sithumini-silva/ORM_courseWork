package org.example.dao.custom.impl;

import javafx.scene.control.Alert;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.PaymentDAO;
import org.example.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean savePay(Payment object) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
        return true;
    }
//    @Override
//    public boolean save(Payment entity) {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        Object payment = session.save(entity);
//        System.out.println(payment);
//
//        if (payment != null) {
//            transaction.commit();
//            session.close();
//            return true;
//        }else{
//            return false;
//        }
//    }

    @Override
    public boolean save(Payment object) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
        return true;
    }
    @Override
    public boolean update(Payment object) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Payment object) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Payment get(Payment object) {
        return null;
    }

    @Override
    public List<Payment> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createNativeQuery("SELECT * FROM payment");
        query.addEntity(Payment.class);
        List<Payment> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public Payment search(String id) {
        return null;
    }

    public List<String> getIds() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" select id from Payment");
        List<String> list = query.getResultList();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public String getCurrentId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select id from Payment order by id desc limit 1");
        String id = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public Payment getObject(String value) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Payment where id = ?1");
        query.setParameter(1,value);
        Payment payment= (Payment) query.uniqueResult();
        transaction.commit();
        session.close();
        return payment;
    }



    @Override
    public boolean savedPayment(Payment entity, Session session) {
        try {
            session.save(entity);
            return true;
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return false;
        }
    }
    public Payment getPatientById(String paymentId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        Query query = session.createQuery("from Payment where id = :id");
        query.setParameter("id", paymentId);


        Payment payment = (Payment) query.uniqueResult();

        transaction.commit();
        session.close();

        return payment;
    }
}
