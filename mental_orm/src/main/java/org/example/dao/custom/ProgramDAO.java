package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Program;
import org.hibernate.Session;

import java.util.List;

public interface ProgramDAO extends CrudDAO<Program> {
    List<String> getIds();

    String getCurrentId();

    Program getObject(String value);
    boolean update(Session session, Program object);
    List<Program> findAll();
    Program getProgramByName(String programName);


}
