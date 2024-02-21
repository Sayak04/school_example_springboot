package com.school.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.exceptions.CsvException;
import com.school.models.User;
import com.school.services.user.IUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    /**
     * API - Add a new User
     * 
     * @param firstName
     * @param lastName
     * @return
     */
    @PostMapping("/add/new-user")
    public ResponseEntity<User> addNewUser(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
        User addedUser = userService.addUser(firstName, lastName);

        return ResponseEntity.ok(addedUser);
    }

    /**
     * API - Add a role to a user
     * 
     * @param id
     * @param roleId
     * @return
     */
    @PostMapping("add/user-role")
    public ResponseEntity<User> addRoleToUser(
            @RequestParam("id") Long id,
            @RequestParam("role_id") Integer roleId) {

        User updatedUser = userService.addNewRoleToUser(id, roleId);

        System.out.println(updatedUser);

        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = userService.getAllUsers();

        if(users.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(users);
    }

    /**
     * API - Get user by ID
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(
            @PathVariable("id") Long id) {

        User requestedUser = userService.getUserById(id);

        if (requestedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(requestedUser);
    }

    /**
     * API - Get users by name
     * 
     * @param name
     * @return
     */
    @GetMapping("/find-users")
    public ResponseEntity<List<User>> getUsersByName(
            @RequestParam("name") String name) {
        List<User> users = userService.getUsersByName(name);

        if (users == null || users.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(users);
    }

    /**
     * API - Get users by role
     * 
     * @param role
     * @return
     */
    @GetMapping("/find-users/role")
    public ResponseEntity<List<User>> getUsersByRole(
            @RequestParam("role") String role) {
        List<User> users = userService.getUsersByRole(role);

        if (users == null || users.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(users);
    }

    /**
     * API - Delete user by id
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(
            @PathVariable("id") Long id) {

        userService.deleteUserById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload/csv")
    public ResponseEntity<Void> addUsersFromCsv(
            @RequestBody MultipartFile file) throws IOException, CsvException {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        userService.printCSV(file);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
