package com.school.services.user;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.opencsv.exceptions.CsvException;
import com.school.models.User;

public interface IUserService {

    public User addUser(String firstName, String lastName);

    public User addNewRoleToUser(Long id, Integer roleId);

    public List<User> getAllUsers();
    
    public User getUserById(Long id);

    public void deleteUserById(Long id);

    public List<User> getUsersByName(String name);

    public List<User> getUsersByRole(String role);

    public void printCSV(MultipartFile file) throws FileNotFoundException, IOException, CsvException;

    public void parseCsv();

}
