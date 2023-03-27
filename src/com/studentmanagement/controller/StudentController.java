package com.studentmanagement.controller;

import com.studentmanagement.enums.Gender;
import com.studentmanagement.enums.Subject;
import com.studentmanagement.model.StudentCreateRequest;
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
        if(student != null){
            var request = new StudentUpdateRequest(id,
                    student.getFullName(),
                    student.getGender(),
                    student.getDob(),
                    student.getMath(),
                    student.getPhysics(),
                    student.getChemistry());
            int selection = -1;
            while (selection != 0){
                System.out.printf("Student: %s - ID: %d%n", student.getFullName(), student.getId());
                System.out.printf("1. Update full name: old: %s - new: %s%n", student.getFullName(), request.getFullName());
                System.out.printf("2. Update gender: old: %s - new: %s%n", Gender.getGenderName(student.getGender()), Gender.getGenderName(request.getGender()));
                System.out.printf("3. Update date of birth: old: %s - new: %s%n", student.getDob().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), request.getDob().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                System.out.printf("4. Update Math scores: old: %.2f - new: %.2f%n", student.getMath(), request.getMath());
                System.out.printf("5. Update Physics scores: old: %.2f - new: %.2f%n", student.getPhysics(), request.getPhysics());
                System.out.printf("6. Update Chemistry scores: old: %.2f - new: %.2f%n", student.getChemistry(), request.getChemistry());
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
        else{
            System.out.println("This student is not exist!");
        }
    }

    public void removeStudent(){
        var id = studentInputService.setId();
        var student = studentService.getById(id);
        if(student != null){
            var isConfirm = studentInputService.confirmRemoveStudent(student.getFullName());
            if(isConfirm)
                System.out.println(studentService.removeStudent(id));
        }
        else{
            System.out.println("This student is not exist!");
        }
    }

    public void searchStudent(){
        String name = studentInputService.setName();
        var students = studentService.getByName(name);
        if(!students.isEmpty())
            studentService.displayStudents(students);
    }

    public void displayStudents(){
        System.out.println("1. Order by GPA ascending");
        System.out.println("2. Order by GPA decreasing");
        System.out.println("3. Order by name ascending");
        System.out.println("4. Order by name decreasing");
        System.out.println("5. Order by ID ascending");
        System.out.println("6. Order by ID decreasing");
        System.out.println("0. Back");
        int selection = studentInputService.select();
        switch (selection){
            case 1:
                studentService.displayStudents(studentService.orderByAverage());
                break;
            case 2:
                studentService.displayStudents(studentService.orderByAverage(true));
                break;
            case 3:
                studentService.displayStudents(studentService.orderByName());
                break;
            case 4:
                studentService.displayStudents(studentService.orderByName(true));
                break;
            case 5:
                studentService.displayStudents(studentService.getStudents());
                break;
            case 6:
                studentService.displayStudents(studentService.getStudents(true));
                break;
            default:
                break;
        }
    }
}
