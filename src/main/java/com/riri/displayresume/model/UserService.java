package com.riri.displayresume.model;

import com.riri.displayresume.repositories.JobRepo;
import com.riri.displayresume.repositories.OrganizationRepo;
import com.riri.displayresume.repositories.RoleRepository;
import com.riri.displayresume.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
/*    @Autowired
    JobRepo jobRepo;
    @Autowired
    OrganizationRepo orgRepo;*/


    @Autowired
//    public UserService(UserRepository userRepository, JobRepo jobRepo, OrganizationRepo orgRepo){
    public UserService(UserRepository userRepository){

            this.userRepository = userRepository;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public Long countByEmail(String email){
        return userRepository.countByEmail(email);
    }
    public User findByUserName(String username){
        return userRepository.findByUserName(username);
    }
    public Role findByRole(String role){return  roleRepository.findByRole(role);}

    public void saveUser(User user){
        // user.addRole(roleRepository.findByRole("USER"));
        user.setRoles(Arrays.asList(roleRepository.findByRole("APPLICANT")));
        user.setEnabled(true);

        userRepository.save(user);
    }
    public void saveAdmin(User user){
        user.setRoles(Arrays.asList(roleRepository.findByRole("ADMIN")));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void saveEmployer(User user){
        user.setRoles(Arrays.asList(roleRepository.findByRole("EMPLOYER")));
        user.setEnabled(true);
        userRepository.save(user);
    }


    public void saveRecruiter(User user){
        user.setRoles(Arrays.asList(roleRepository.findByRole("RECRUITER")));
        user.setEnabled(true);
        userRepository.save(user);
    }


}