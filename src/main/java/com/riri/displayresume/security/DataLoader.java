package com.riri.displayresume.security;

import com.riri.displayresume.model.Role;
import com.riri.displayresume.model.User;
import com.riri.displayresume.repositories.RoleRepository;
import com.riri.displayresume.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public void run(String...strings) throws Exception{
        System.out.println("Loading data ....");
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));


        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");


        User user = new
                User("admin@admin.com","adminpass","Admin","Rocks", true,"admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);


        user = new
                User("employer@employer.com", "emppass", "Employer", "Manager",true, "employer");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new
                User("applicant@applicant.com","apppass","Applicant","Riri",true,"applicant");
        user.setRoles(Arrays.asList(userRole,adminRole));
        userRepository.save(user);


    }
}