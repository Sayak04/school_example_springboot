package com.school.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.models.User;
import com.school.services.user.IUserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;
    
    @PostMapping("/add/new-user")
    public ResponseEntity<User> addNewUser(
        @RequestParam("firstName") String firstName,
        @RequestParam("lastName") String lastName
    ) {
        User addedUser = userService.addUser(firstName, lastName);

        return ResponseEntity.ok(addedUser);
    }

    @PostMapping("add/user-role")
    public ResponseEntity<User> addRoleToUser(
        @RequestParam("id") Long id,
        @RequestParam("role_id") Integer roleId
    ) {

        User updatedUser = userService.addNewRoleToUser(id, roleId);

        System.out.println(updatedUser);

        return ResponseEntity.ok(updatedUser);
    }
}
