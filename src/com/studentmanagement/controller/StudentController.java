package com.studentmanagement.controller;

import com.studentmanagement.enums.Gender;
import com.studentmanagement.enums.Subject;
import com.studentmanagement.model.StudentCreateRequest;
import com.studentmanagement.model.StudentDTO;
import com.studentmanagement.model.StudentUpdateRequest;
import com.studentmanagement.service.StudentInputService;
import com.studentmanagement.service.StudentService;

import java.time.format.DateTimeFormatter;

public class StudentController {
    private final StudentService studentService;
    private final StudentInputService studentInputService;

    public StudentController(){
        this.studentService = StudentService.getstudentService();
        this.studentInputService = new StudentInputService();
    }



    public void createStudent(){
        var request = new StudentCreateRequest();

        request.setFullName(studentInputService.setFullName());
        request.setGender(studentInputService.setGender());
        request.setDob(studentInputService.setDob());
        request.setMath(studentInputService.setSubjectScores(Subject.MATH));
        request.setPhysics(studentInputService.setSubjectScores(Subject.PHYSICS));
        request.setChemistry(studentInputService.setSubjectScores(Subject.CHEMISTRY));

        System.out.println(studentService.createStudent(request));
    }

    public void updateStudent(){
        var id = studentInputService.setId();
        var student = studentService.getById(id);
        if(student == null)
            System.out.println("This student is not exist!");
        var request = new StudentUpdateRequest(id,
                student.getFullName(),
                student.getGender(),
                student.getDob(),
                student.getMath(),
                student.getPhysics(),
                student.getChemistry());
        int selection = -1;
        while (selection != 0){
            System.out.println(String.format("Student: %s - ID: %d", student.getFullName(), student.getId()));
            System.out.println(String.format("1. Update full name: old: %s - new: %s", student.getFullName(), request.getFullName()));
            System.out.println(String.format("2. Update gender: old: %s - new: %s", Gender.getGenderName(student.getGender()), Gender.getGenderName(request.getGender())));
            System.out.println(String.format("3. Update date of birth: old: %s - new: %s", student.getDob().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), request.getDob().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            System.out.println(String.format("4. Update Math scores: old: %.2f - new: %.2f", student.getMath(), request.getMath()));
            System.out.println(String.format("5. Update Physics scores: old: %.2f - new: %.2f", student.getPhysics(), request.getPhysics()));
            System.out.println(String.format("6. Update Chemistry scores: old: %.2f - new: %.2f", student.getChemistry(), request.getChemistry()));
            System.out.println("7. Save");
            System.out.println("0. Back");
            System.out.println("Choose service:");
            selection = studentInputService.select();
            switch (selection){
                case 1:
                    request.setFullName(studentInputService.setFullName());
                    break;
                case 2:
                    request.setGender(studentInputService.setGender());
                    break;
                case 3:
                    request.setDob(studentInputService.setDob());
                    break;
                case 4:
                    request.setMath(studentInputService.setSubjectScores(Subject.MATH));
                    break;
                case 5:
                    request.setPhysics(studentInputService.setSubjectScores(Subject.PHYSICS));
                    break;
                case 6:
                    request.setChemistry(studentInputService.setSubjectScores(Subject.CHEMISTRY));
                    break;
                case 7:
                    System.out.println(studentService.updateStudent(request));
                    student = studentService.getById(id);
                    break;
                default:
                    break;
            }
        }
    }

    public void removeStudent(){
        var id = studentInputService.setId();
        var student = studentService.getById(id);
        if(student == null)
            System.out.println("This student is not exist!");

        var isConfirm = studentInputService.confirmRemoveStudent(student.getFullName());
        if(isConfirm)
            System.out.println(studentService.removeStudent(id));
    }

    public void searchStudent(){
        String name = studentInputService.setName();
        var students = studentService.getByName(name);
        for (StudentDTO student : students) {
            System.out.println(String.format("ID: %d - %s", student.getId(), student.getFullName()));
            System.out.println(String.format("Gender: %s", Gender.getGenderName(student.getGender())));
            System.out.println(String.format("Age: %d", student.getAge()));
            System.out.println(String.format("Math: %.2f", student.getMath()));
            System.out.println(String.format("Physics: %.2f", student.getPhysics()));
            System.out.println(String.format("Chemistry: %.2f", student.getChemistry()));
            System.out.println(String.format("Average: %.2f", student.getAverage()));
            System.out.println(String.format("Classification: %s", student.getClassification()));
            System.out.println("*".repeat(20));
        }
    }
}
