package com.school.services.role;

import org.springframework.stereotype.Service;

import com.school.models.Role;
import com.school.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role addRole(String title) {
        
        Role role = new Role();

        role.setTitle(title);

        return roleRepository.save(role);
    }
    
}
