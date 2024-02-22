package com.school.services.user;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.school.models.Role;
import com.school.models.User;
import com.school.repository.RoleRepository;
import com.school.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Override
    public User addUser(String firstName, String lastName) {
        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
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

    @Override
    public List<User> getUsersByName(String name) {
        return userRepository.getUsersByName(name);
    }

    @Override
    public List<User> getUsersByRole(String role) {
        return userRepository.getUsersByRole(role);
    }

    @Override
    public void printCSV(MultipartFile file) throws IOException, CsvException {
        File convertedFile = convert(file);
        CSVReader reader = new CSVReader(new FileReader(convertedFile));
        List<String[]> rows = reader.readAll();

        for (String[] row : rows) {
            System.out.println(row[2] + ", " + row[3]);

            User user = new User();
            user.setFirstName(row[2]);
            user.setLastName(row[3]);

            userRepository.save(user);
        }

        reader.close();
    }

    private File convert(MultipartFile file) throws IOException {
        File convertedFile = File.createTempFile("temp", null);

        file.transferTo(convertedFile);

        return convertedFile;
    }

    @Override
    public void parseCsv() {
        
        JobParameters jobParameters = new JobParametersBuilder()
            .addLong("startAt", System.currentTimeMillis())
            .toJobParameters();

        try {
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
