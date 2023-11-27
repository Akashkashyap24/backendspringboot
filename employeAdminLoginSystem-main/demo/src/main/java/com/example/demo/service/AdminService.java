package com.example.demo.service;


import com.example.demo.entity.Admin;
import com.example.demo.entity.Employee;
import com.example.demo.enums.Degree;
import com.example.demo.enums.Status;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    public String addAdmin(Admin admin) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String encryptedPassword = bcrypt.encode(admin.getPassword());
        admin.setPassword(encryptedPassword);
        adminRepository.save(admin);
        return admin.getName()+" added successfully";
    }

    public String loginAdmin(String email, String password) throws Exception {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        Optional<Admin> a = adminRepository.findById(email);
//        Admin admin = adminRepository.findById(email).get();
        if(a.isPresent()){
            Admin admin = a.get();
            if(bcrypt.matches(password, admin.getPassword())){
                return admin.getName()+" login successfully";
            }
            else return "Incorrect Password";
        }
        throw new Exception( email +" user is not exist");
    }

    public void updateEmployeeStatusByEmail(String email) throws Exception {
        // Employee employee = employeeRepository.findById(email).get();
        Optional<Employee> e=employeeRepository.findById(email);
        if(e.isPresent()){
            Employee employee = e.get();
            if(employee.getStatus().equals(Status.PENDING)){
                if((employee.getDegree().equals(Degree.BBA) || employee.getDegree().equals(Degree.BCA) || employee.getDegree().equals(Degree.BTECH)) && employee.getIsExperienceLetter().equals("yes")){
                    //employee.setStatus(Status.valueOf("SUCCESS"));
                    employee.setStatus(Status.SUCCESS);

                }
                else{
                    employee.setStatus(Status.NOTELIGIBLE);
                }
                employeeRepository.save(employee);
            }
        }
        else{
            throw new Exception("you should write correct email");
        }

    }
}
