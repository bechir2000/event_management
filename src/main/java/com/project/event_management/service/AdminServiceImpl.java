package com.project.event_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.event_management.entities.Admin;
import com.project.event_management.exceptions.UserNotFoundException;
import com.project.event_management.repository.AdminRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AdminServiceImpl implements AdminService{
    private AdminRepository adminRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Admin getAdmin(Long id) {
        Optional<Admin> admin = adminRepository.findById(id); // look for admin in the data base
        if (admin.isPresent()){ //if the admin exists
                return admin.get(); //return it
        } else {
            throw new UserNotFoundException("admin",id);
        }
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword())); //save with encrypted password
        return adminRepository.save(admin);
    }

    @Override
    public Admin updateAdmin(Long id,String fName, String lName,String phoneNumber, String password){
        Optional<Admin> oldAdmin = adminRepository.findById(id); // getting adin from the data base
        if(oldAdmin.isPresent()){ //if the admin exists
            Admin admin= oldAdmin.get();
            admin.setFirstName(fName); //update
            admin.setLastName(lName);
            admin.setPhoneNumber(phoneNumber);
            admin.setPassword(bCryptPasswordEncoder.encode(password));
            return adminRepository.save(admin); //save the updated admin
        }else {
            throw new UserNotFoundException("admin", id);
        }
    }

    @Override
    public void deleteAdmin(Long id) {
        Optional<Admin> admin=adminRepository.findById(id);
        if(admin.isPresent()){ //if the admin exists 
            adminRepository.deleteById(id); //delete it
        } else {
            throw new UserNotFoundException("admin",id);
        }
        
    }

    @Override
    public List<Admin> getAdmins() {
        return (List<Admin>)adminRepository.findAll() ;
    }
     
}
