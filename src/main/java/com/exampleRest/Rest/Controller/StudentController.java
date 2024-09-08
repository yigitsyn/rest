package com.exampleRest.Rest.Controller;

import com.exampleRest.Rest.Exception.StudentIDZeroException;
import com.exampleRest.Rest.bean.Student;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    //return object
    //http://localhost:8080/student
    @GetMapping("student")
    public Student getStudent(){
        // obje döndüğümüzde format json oluyor.
        Student student = new Student(1,"Yigit","Sayin");
        return student;
    }

    //returns List
    //http://localhost:8080/students
    @GetMapping("/students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<Student>();
        students.add(new Student(1,"Yigit","Sayin"));
        students.add(new Student(2,"Yigit","Sayin"));
        students.add(new Student(3,"Yigit","Sayin"));
        students.add(new Student(4,"Yigit","Sayin"));
        return students;
    }
    // Spring Boot REST API with Path Variable
    //{id} _ URL template variable
    //GETMapping variable name ile input variable farklı isimde
    //http://localhost:8080/students/1
    // StudentIDZeroException için bu classın içinde method tanımlanabilir ama clean code bakımından GlobalExceptionHandler daha iyi
    @GetMapping("students/{id}")
    public Student studentPathVariable(@PathVariable("id") int studentId){
        if(studentId == 0){
           throw new StudentIDZeroException("Input cannot be zero.") ;
        }else
        return new Student(studentId,"Yigit","Sayinn");

    }

    //http://localhost:8080/students/1/yig/say
    //birden fazla değer
    @GetMapping("students/{id}/{firstName}/{lastName}")
    public Student studentPathVariableAllVariable(@PathVariable("id") int studentId,
                                                  @PathVariable String firstName,
                                                  @PathVariable String lastName){
        if(studentId == 0){
            throw new StudentIDZeroException("Input cannot be zero.") ;
        }else
            return new Student(studentId,
                    firstName,lastName);

    }


    // Spring Boot REST API with Path Variable
    //{id} _ URL template variable
    //http://localhost:8080/students/1
    //GETMapping variable name ile input variable aynı isimde
    @GetMapping("students/path/{id}")
    public Student studentPathVariableAW(@PathVariable int id){
        return new Student(id,"Yigit","Sayinn");

    }
}


