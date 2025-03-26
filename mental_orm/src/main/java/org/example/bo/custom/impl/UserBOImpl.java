package org.example.bo.custom.impl;

import org.example.bo.custom.UserBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.UserDAO;
import org.example.entity.User;
import org.example.models.UserDTO;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
   private UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    public UserBOImpl(){

    }
    public UserBOImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserById(String userId) throws SQLException {
        return userDAO.getUserById(userId);
    }

    @Override
    public boolean checkCredentials(String userId, String password) throws SQLException {
        // Get the stored password hash from the database
        String storedHash = userDAO.getPasswordHashByUserId(userId);
        if (storedHash == null) {
            return false; // User not found
        }

        // Check if the entered password matches the stored hash
        return BCrypt.checkpw(password, storedHash);
    }

    public boolean save(UserDTO userDTO) {
        return userDAO.save(new User(userDTO.getId(),userDTO.getName(),userDTO.getRole(),userDTO.getTel(),userDTO.getEmail(),userDTO.getPassword()));
    }

    @Override
    public UserDTO searchByID(String userID) {
        User user = userDAO.search(userID);
        return new UserDTO(user.getUserId(),user.getRole(),user.getName(),user.getPassword(),user.getEmail(),user.getTel());
    }



    @Override
    public boolean update(UserDTO userDTO) {
        return userDAO.update( new User(userDTO.getId(),userDTO.getName(),userDTO.getRole(),userDTO.getTel(),userDTO.getEmail(),userDTO.getPassword()));
    }

    @Override
    public boolean delete(UserDTO userDTO) {
        return userDAO.delete(new User(userDTO.getId(),userDTO.getName(),userDTO.getRole(),userDTO.getTel(),userDTO.getEmail(),userDTO.getPassword()));
    }
    @Override
    public UserDTO get(String value) {
        User object = userDAO.getObject(value);
        return new UserDTO(object.getUserId(),object.getName(),object.getRole(),object.getTel(),object.getEmail(),object.getPassword());
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> all = userDAO.getAll();
        for (User user : all){
            userDTOS.add(new UserDTO(user.getUserId(),user.getName(),user.getRole(),user.getTel(),user.getEmail(),user.getPassword()));
        }
        return userDTOS;
    }
    @Override
    public List<String> getIds() {
        return userDAO.getIds();
    }

    @Override
    public String getCurrentId() {
        return userDAO.getCurrentId();
    }

    @Override
    public UserDTO getUsersIdAndPasswordAndRole(String userId, String roll) throws SQLException {
        User user = userDAO.getUsersIdPasswordAndRole(userId, roll);

        if (user != null) {
            return new UserDTO(user.getUserId(), user.getName(), user.getRole(), user.getTel(), user.getEmail(), user.getPassword());
        }
        return null;
    }



}
