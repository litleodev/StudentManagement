package com.studentmanagement.service;

import com.studentmanagement.common.StringExtensions;
import com.studentmanagement.entity.Student;
import com.studentmanagement.enums.Gender;
import com.studentmanagement.model.StudentCreateRequest;
import com.studentmanagement.model.StudentDTO;
import com.studentmanagement.model.StudentUpdateRequest;

import java.util.*;

public class StudentService {
    private int id = 1;
    private final Map<Integer, Student> students = new HashMap<>();
    private static StudentService studentService;

    private StudentService() {
    }

    public static StudentService getstudentService() {
        if (studentService == null) {
            studentService = new StudentService();
        }
        return studentService;
    }

    public String createStudent(StudentCreateRequest request){
        try {
            var student = new Student(request.getFullName(),
                    request.getGender(),
                    request.getDob(),
                    request.getMath(),
                    request.getPhysics(),
                    request.getChemistry());

            students.put(id, student);
            ++id;
            return "Create new student success!";
        }
        catch (Exception e){
            return "Cannot create new student";
        }
    }

    public StudentDTO getById(int id){
        var student = students.get(id);
        if(student == null)
            return null;
        return new StudentDTO(id, student.getFullName(),
                student.getGender(),
                student.getDob(),
                student.getMath(),
                student.getPhysics(),
                student.getChemistry());
    }

    public String updateStudent(StudentUpdateRequest request){
        try {
            var student = new Student(request.getFullName(),
                    request.getGender(),
                    request.getDob(),
                    request.getMath(),
                    request.getPhysics(),
                    request.getChemistry());

            students.replace(request.getId(), student);
            return "Update student success!";
        }
        catch (Exception e){
            return "Cannot update student";
        }
    }

    public String removeStudent(int id){
        try{
            var isExist = students.containsKey(id);
            if(!isExist)
                return "Student is not exist";
            students.remove(id);
            return "Remove student success!";
        }
        catch (Exception e){
            return "Cannot remove student";
        }
    }

    public List<StudentDTO> getByName(String name){
        List<StudentDTO> result = new ArrayList<>();
        if(name.isBlank())
            return result;
        for (var entry : students.entrySet())
        {
            if(entry.getValue().getFullName().equalsIgnoreCase(name)){
                var student = entry.getValue();
                result.add(new StudentDTO(entry.getKey(),
                        student.getFullName(),
                        student.getGender(),
                        student.getDob(),
                        student.getMath(),
                        student.getPhysics(),
                        student.getChemistry()));
            }
        }
        return result;
    }
    public List<StudentDTO> getStudents(boolean decreasing){
        List<StudentDTO> result = new ArrayList<>();
        for (var entry : students.entrySet()) {
            var student = entry.getValue();
            result.add(new StudentDTO(entry.getKey(),
                    student.getFullName(),
                    student.getGender(),
                    student.getDob(),
                    student.getMath(),
                    student.getPhysics(),
                    student.getChemistry()));
        }
        if(decreasing)
            result.sort((o1, o2) -> o2.getId() - o1.getId());

        return result;
    }

    public List<StudentDTO> getStudents(){
        return getStudents(false);
    }

    public List<StudentDTO> orderByAverage(boolean decreasing){
        var result = getStudents();
        result.sort((o1, o2) -> !decreasing
                ? Double.compare(o1.getAverage(), o2.getAverage())
                : Double.compare(o2.getAverage(), o1.getAverage()));

        return result;
    }

    public List<StudentDTO> orderByAverage(){
        return orderByAverage(false);
    }

    public List<StudentDTO> orderByName(boolean decreasing){
        var result = getStudents();
        result.sort((o1, o2) -> {
            String[] s1 = o1.getFullName().split(" ");
            String[] s2 = o2.getFullName().split(" ");
            int compare = !decreasing ? StringExtensions.stringCompare(s1[s1.length - 1], s2[s2.length - 1])
                    : StringExtensions.stringCompare(s2[s2.length - 1], s1[s1.length - 1]);
            if(compare != 0)
                return compare;

            return !decreasing ? StringExtensions.stringCompare(o1.getFullName(), o2.getFullName())
                    : StringExtensions.stringCompare(o2.getFullName(), o1.getFullName());
        });

        return result;
    }

    public List<StudentDTO> orderByName(){
        return orderByName(false);
    }

    public void displayStudents(List<StudentDTO> students){
        for (var student: students){
            System.out.printf("ID: %d - %s%n", student.getId(), student.getFullName());
            System.out.printf("Gender: %s%n", Gender.getGenderName(student.getGender()));
            System.out.printf("Age: %d%n", student.getAge());
            System.out.printf("Math: %.2f%n", student.getMath());
            System.out.printf("Physics: %.2f%n", student.getPhysics());
            System.out.printf("Chemistry: %.2f%n", student.getChemistry());
            System.out.printf("Average: %.2f%n", student.getAverage());
            System.out.printf("Classification: %s%n", student.getClassification());
            System.out.println("*".repeat(20));
        }
    }
}
