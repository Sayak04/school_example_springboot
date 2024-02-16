package com.school.services.user;

import com.school.models.User;

public interface IUserService {

    User addUser(String firstName, String lastName);

    User addNewRoleToUser(Long id, Integer roleId);
}
