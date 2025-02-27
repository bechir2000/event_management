package com.project.event_management.service;

import java.util.List;

import com.project.event_management.entities.Admin;

public interface AdminService {
    Admin getAdmin(Long id);
    Admin saveAdmin(Admin admin);
    void deleteAdmin(Long id);
    Admin updateAdmin(Long id,String fName, String lName,String phoneNumber, String password);
    List<Admin> getAdmins();
}
