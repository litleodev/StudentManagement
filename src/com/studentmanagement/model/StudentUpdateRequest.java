package com.studentmanagement.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class StudentUpdateRequest {
    private int id;
    private String fullName;
    private int gender;
    private LocalDate dob;
    private double math;
    private double physics;
    private double chemistry;

    public StudentUpdateRequest(int id, String fullName, int gender, LocalDate dob, double math, double physics, double chemistry){
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.math = math;
        this.physics = physics;
        this.chemistry = chemistry;
    }
}
