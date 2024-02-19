package com.school.services.role;

import java.util.List;

import com.school.models.Role;

public interface IRoleService {

    Role addRole(String title);

    List<Role> getRoleByText(String text);        
}
