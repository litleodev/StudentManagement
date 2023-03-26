package com.studentmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor
public class Student {
    private String fullName;
    private int gender;
    private LocalDateTime dob;
    private double math;
    private double physics;
    private double chemistry;
}
