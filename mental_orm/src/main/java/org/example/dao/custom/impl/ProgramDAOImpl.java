package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.ProgramDAO;
import org.example.entity.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {
    @Override
    public boolean save(Program object) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Program object) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Session session, Program object) {
        session.update(object);
        return true;
    }



    @Override
    public boolean delete(Program value) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(value);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Program get(Program object) {
        return null;
    }

    @Override
    public List<Program> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Program ");
        List<Program> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public Program search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Program where code =?1");
        query.setParameter(1, id);
        Program program = (Program) query.uniqueResult();
        transaction.commit();
        return program;
    }

    @Override
    public List<String> getIds() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createNativeQuery("select code from Item");
        List<String> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public String getCurrentId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select code from Program order by code desc limit 1");
        String id = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public Program getObject(String value) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Program where code = ?1");
        query.setParameter(1,value);
        Program item = (Program) query.uniqueResult();
        System.out.println(item);
        transaction.commit();
        session.close();
        return item;
    }

    public Program getProgramByCode(String programCode) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Program where code = :code");
        query.setParameter("code", programCode);

        Program program = (Program) query.uniqueResult();

        transaction.commit();
        session.close();

        return program;
    }

    @Override
    public List<Program> findAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Program", Program.class);
        List<Program> programs = query.getResultList();
        transaction.commit();
        session.close();
        return programs;
    }

    @Override
    public Program getProgramByName(String programName) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Program where name = :programName");
        query.setParameter("programName", programName);

        Program program = (Program) query.uniqueResult();

        transaction.commit();
        session.close();

        return program;
    }

}
