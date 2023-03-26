package com.studentmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class Student {
    private String fullName;
    private int gender;
    private LocalDate dob;
    private double math;
    private double physics;
    private double chemistry;

    public Student(String fullName, int gender, LocalDate dob, double math, double physics, double chemistry){
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.math = math;
        this.physics = physics;
        this.chemistry = chemistry;
    }
}
