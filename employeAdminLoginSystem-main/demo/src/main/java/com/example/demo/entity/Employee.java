package com.example.demo.entity;

import com.example.demo.enums.Degree;
import com.example.demo.enums.Designation;
import com.example.demo.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    private String email;
    private String name;
    private String password;
    private String phoneNo;
    @Enumerated(EnumType.STRING)
    private Designation designation;
    @Enumerated(EnumType.STRING)
    private Degree degree;
    private String isExperienceLetter;
    @Enumerated(EnumType.STRING)
    private Status status;
    Employee(String email ,String name ,String password ,String phoneNo ,Designation designation,Degree degree,String isExperienceLetter,Status status){
        this.name=name;
        this.email=email;
        this.password=password;
        this.phoneNo=phoneNo;
        this.designation=designation;
        this.degree=degree;
        this.isExperienceLetter=isExperienceLetter;
        this.status=Status.PENDING;

    }
}
