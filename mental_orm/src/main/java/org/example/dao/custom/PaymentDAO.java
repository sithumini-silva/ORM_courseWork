package org.example.dao.custom;


import org.example.dao.CrudDAO;
import org.example.entity.Payment;
import org.hibernate.Session;

import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {
    List<String> getIds();
    String getCurrentId();
    Payment getObject(String value);

    public boolean savedPayment(Payment entity, Session session);

    boolean savePay(Payment payment);
}
