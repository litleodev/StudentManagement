package com.studentmanagement.entity;

public class Student {
    private String fullName;
    private int gender;
    private int age;
    private double math;
    private double physics;
    private double chemistry;

    public Student(String fullName, int gender, int age, double math, double physics, double chemistry){
        this.fullName = fullName;
        this.gender = gender;
        this.age = age;
        this.math = math;
        this.physics = physics;
        this.chemistry = chemistry;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMath() {
        return math;
    }

    public void setMath(double math) {
        this.math = math;
    }

    public double getPhysics() {
        return physics;
    }

    public void setPhysics(double physics) {
        this.physics = physics;
    }

    public double getChemistry() {
        return chemistry;
    }

    public void setChemistry(double chemistry) {
        this.chemistry = chemistry;
    }
}
