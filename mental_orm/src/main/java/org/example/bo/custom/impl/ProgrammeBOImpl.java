package org.example.bo.custom.impl;


import org.example.bo.custom.ProgrammeBO;
import org.example.config.FactoryConfiguration;
import org.example.dao.DAOFactory;
import org.example.dao.custom.ProgramDAO;
import org.example.entity.Program;
import org.example.models.ProgramDTO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeBOImpl implements ProgrammeBO {
    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROGRAMME);
    @Override
    public boolean save(ProgramDTO programDTO) {
        return programDAO.save(new Program(programDTO.getCode(), programDTO.getName(), programDTO.getPrice(), programDTO.getDuration()));
    }

    @Override
    public boolean update(ProgramDTO programDTO) {
        return programDAO.update( new Program(programDTO.getCode(), programDTO.getName(), programDTO.getPrice(), programDTO.getDuration()));
    }

    @Override
    public boolean delete(ProgramDTO programDTO) {
        return programDAO.delete(new Program(programDTO.getCode(), programDTO.getName(), programDTO.getPrice(), programDTO.getDuration()));
    }

    @Override
    public ProgramDTO get(String value) {
        Program item = programDAO.getObject(value);
        return new ProgramDTO(item.getCode(), item.getName(), item.getPrice(), item.getDuration());
    }

    @Override
    public List<String> getIds() {
        return programDAO.getIds();
    }

    @Override
    public List<ProgramDTO> getAll() {
        List<Program> all = programDAO.getAll();
        List<ProgramDTO> itemDtos = new ArrayList<>();
        for (Program item : all){
            itemDtos.add(new ProgramDTO(item.getCode(), item.getName(), item.getPrice(), item.getDuration()));
        }
        return itemDtos;
    }

    @Override
    public String getCurrentId() {
        return programDAO.getCurrentId();
    }

    @Override
    public List<ProgramDTO> getAllPrograms() {
        List<Program> programs = programDAO.getAll();
        List<ProgramDTO> prgList = new ArrayList<>();

        for (Program program : programs){
            ProgramDTO programDTO = new ProgramDTO(program.getCode(),program.getName(),program.getPrice(),program.getDuration());
            prgList.add(programDTO);
        }
        return prgList;
    }

    @Override
    public Program getProgramByName(String programName) {
        return programDAO.getProgramByName(programName);
    }

    @Override
    public Program getProgramById(String programId) throws Exception {
        Session session = null;
        try {
            // Open a session
            session = FactoryConfiguration.getInstance().getSession();

            // Create a query to fetch the Program by ID
            String hql = "FROM Program WHERE code = :programId";
            Query<Program> query = session.createQuery(hql, Program.class);
            query.setParameter("programId", programId);

            // Get the result (Single Program)
            Program program = query.uniqueResult();

            return program;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error retrieving program by ID.");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ProgramDTO searchById(String programId) {
        Program program = programDAO.search(programId);
        return new ProgramDTO(program.getCode(),program.getName(),program.getPrice(),program.getDuration());
    }
}
