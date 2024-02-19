package com.school.services.user;

import com.school.models.Role;
import com.school.models.User;
import com.school.repository.RoleRepository;
import com.school.repository.UserRepository;

import lombok.RequiredArgsConstructor;

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

    @Override
    public User getUserById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }

        return null;
    }

    @Override
    public User addNewRoleToUser(Long id, Integer roleId) {
        // get user and role by id
        Optional<User> optionalUser = userRepository.findById(id);
        Optional<Role> optionalRole = roleRepository.findById(roleId);

        if (optionalUser.isPresent() && optionalRole.isPresent()) {
            User user = optionalUser.get();
            Role role = optionalRole.get();
            user.addRole(role); // Update user's roles
            userRepository.save(user);
            return user;
        }
        return null;

        // TODO:- Add same for saving in role table
    }

    @Override
    public void deleteUserById(Long id) {
        
        userRepository.deleteById(id);

    }


}
