package org.example.bo.custom;



import org.example.bo.SuperBO;
import org.example.entity.Program;
import org.example.models.ProgramDTO;

import java.util.List;

public interface ProgrammeBO extends SuperBO {
    public boolean save(ProgramDTO itemDto);
    public boolean update(ProgramDTO itemDto);
    public boolean delete(ProgramDTO itemDto);
    public ProgramDTO get(String value);

    List<String> getIds();

    List<ProgramDTO> getAll();

    String getCurrentId();


    Program getProgramByName(String programName);
    Program getProgramById(String programId) throws Exception;
    public ProgramDTO searchById(String programId);
    public List<ProgramDTO> getAllPrograms();
}
