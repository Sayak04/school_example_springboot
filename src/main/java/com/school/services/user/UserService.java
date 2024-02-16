package com.school.services.user;

import com.school.models.Role;
import com.school.models.User;
import com.school.repository.RoleRepository;
import com.school.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User addUser(String firstName, String lastName) {
        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);

        return userRepository.save(user);
    }

    public User addNewRoleToUser(Long id, Integer roleId) {
        // get user and role by id
        Optional<User> optionalUser = userRepository.findById(id);
        Optional<Role> optionalRole = roleRepository.findById(roleId);

        User user = null;
        Role role = null;

        if(optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        if(optionalRole.isPresent()) {
            role = optionalRole.get();
        }

        if(user == null || role == null) {
            return null;
        }

        List<Role> rolesOfCurrUser = user.getRoles();
        rolesOfCurrUser.add(role);

        return userRepository.save(user);

        // TODO:- Add same for saving in role table
    }
}
