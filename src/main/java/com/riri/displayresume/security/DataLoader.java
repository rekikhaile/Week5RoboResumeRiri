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
                User("dave@admin.com","beastmaster","AdminDave","Rocks", true,"davewolf");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);



    }
}