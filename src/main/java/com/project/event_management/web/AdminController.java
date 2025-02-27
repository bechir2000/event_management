package com.project.event_management.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.event_management.entities.Admin;
import com.project.event_management.service.AdminService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor 
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    AdminService adminService;

    @GetMapping("/{id}")
        public ResponseEntity<Admin> getAdmin(@PathVariable Long id){
            return new ResponseEntity<>(adminService.getAdmin(id), HttpStatus.OK);
        }

    @PostMapping("/register")
    public ResponseEntity<Admin> saveAdmin(@Valid @RequestBody Admin admin){
        adminService.saveAdmin(admin);
        return new ResponseEntity<Admin>( HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin admin, @PathVariable Long id) {
        return new ResponseEntity<>(adminService.updateAdmin(id, admin.getFirstName(), admin.getLastName(), admin.getPhoneNumber(), admin.getPassword()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);    
    }

    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAdmins() {
        return new ResponseEntity<>(adminService.getAdmins(), HttpStatus.OK);
    }


}
    

