package com.example.demo.controller;


import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @PostMapping("/add")
    public String addAdmin(@RequestBody Admin admin){
        return adminService.addAdmin(admin);
    }
    @GetMapping("/login/{email}/{password}")
    public String  loginAdmin(@PathVariable("email") String email,@PathVariable("password") String password) throws Exception {
        return adminService.loginAdmin(email,password);
    }
    @PutMapping("/status/{email}")
    public void updateEmployeeStatusByEmail(@PathVariable("email") String email) throws Exception{
        adminService.updateEmployeeStatusByEmail(email);
    }

}
