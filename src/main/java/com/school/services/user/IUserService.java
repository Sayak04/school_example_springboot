package com.school.services.user;

import java.util.List;

import com.school.models.User;

public interface IUserService {

    public User addUser(String firstName, String lastName);

    public User addNewRoleToUser(Long id, Integer roleId);

    public User getUserById(Long id);

    public void deleteUserById(Long id);

    public List<User> getUsersByName(String name);

    public List<User> getUsersByRole(String role);
}
