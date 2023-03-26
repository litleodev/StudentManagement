package com.studentmanagement.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class StudentCreateRequest {
    private String fullName;
    private int gender;
    private LocalDate dob;
    private double math;
    private double physics;
    private double chemistry;

    public StudentCreateRequest(){}
}
