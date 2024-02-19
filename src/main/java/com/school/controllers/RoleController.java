package com.school.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.models.Role;
import com.school.services.role.IRoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    
    private final IRoleService roleService;

    @PostMapping("add/new-role")
    public ResponseEntity<Role> addNewRole(
        @RequestParam("title") String title
    ) {
        Role addedRole = roleService.addRole(title);

        return ResponseEntity.ok(addedRole);
    }


    // api to search a role
    @GetMapping("/find-roles")
    public ResponseEntity<List<Role>> searchRolesByText(
        @RequestParam("text") String text
    ) {
        List<Role> searchedRoles = roleService.getRoleByText(text);

        return ResponseEntity.ok(searchedRoles);
    }
}
