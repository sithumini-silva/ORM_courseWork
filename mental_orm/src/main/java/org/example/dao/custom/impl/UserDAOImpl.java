package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.UserDAO;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private Connection connection;

    public UserDAOImpl(){

    }

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getUserById(String userId) throws SQLException {
        String query = "SELECT * FROM users WHERE userId = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, userId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("userId"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("tel"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        }
        return null;
    }

    @Override
    public String getPasswordHashByUserId(String userId) {
        try ( Session session = FactoryConfiguration.getInstance().getSession()) {
            String hql = "SELECT u.password FROM User u WHERE u.userId = :userId";
            return session.createQuery(hql, String.class)
                    .setParameter("userId", userId)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save(User object) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User object) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(User object) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public User get(User object) {
        return null;
    }

    @Override
    public List<User> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createNativeQuery("SELECT * FROM User");
        query.addEntity(User.class);
        List<User> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public User search(String userID) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from User where userId =?1");
        query.setParameter(1, userID);
        User user = (User) query.uniqueResult();
        transaction.commit();
        return user;
    }
    @Override
    public List<String> getIds() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" select id from User");
        List<String> list = query.getResultList();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public String getCurrentId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select id from User order by userId desc limit 1");
        String id = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public User getObject(String value) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where userId = ?1");
        query.setParameter(1,value);
        User user = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return user;
    }

        @Override
        public User getUsersIdPasswordAndRole(String userId,String role) throws SQLException {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from User where userId = :userId  and role = :role");
            query.setParameter("userId", userId);
//            query.setParameter("password", password);
            query.setParameter("role", role);
            User user = (User) query.uniqueResult();
            transaction.commit();
            session.close();

            return user;
        }


}
