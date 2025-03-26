package org.example.bo.custom;



import org.example.bo.SuperBO;
import org.example.models.PaymentDTO;

import java.util.List;

public interface PaymentBO extends SuperBO {
  boolean saves(PaymentDTO paymentDTO);
    //public boolean exsistCustomer(String id) throws SQLException, ClassNotFoundException ;

  boolean save(PaymentDTO paymentDTO);

    public boolean update(PaymentDTO paymentDTO);
    public boolean delete(PaymentDTO object);
    public PaymentDTO get(String value);

    List<PaymentDTO> getAll();

    List<String> getIds();

    String getCurrentId();

}
