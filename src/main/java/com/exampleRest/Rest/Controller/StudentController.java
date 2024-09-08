package com.exampleRest.Rest.Controller;

import com.exampleRest.Rest.Exception.StudentIDZeroException;
import com.exampleRest.Rest.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    // @GetMapping(gelen http get isteğinin eşlemek için)
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
    //@PathVariable is used to extract values directly from the URI path itself.
    //GETMapping variable name ile input variable aynı isimde
    @GetMapping("students/path/{id}")
    public Student studentPathVariableAW(@PathVariable int id){
        return new Student(id,"Yigit","Sayinn");

    }

    // Spring boot Rest API with RequestParam
    //http://localhost:8080/students/query?id=1
    //@RequestParam is used to extract query parameters from the URL.
    @GetMapping("students/query")
    public Student studentRequestVariable(@RequestParam int id){
        return new Student(id,"yig","sayin");
    }
    //http://localhost:8080/students/query/studentRequestVariableAll?id=1&firstName=yig&lastName=yig
    @GetMapping("students/query/studentRequestVariableAll")
    public Student studentRequestVariableAll(@RequestParam int id, @RequestParam("firstName") String first,@RequestParam("lastName") String last){
        return new Student(id,first,last);
    }

    // Spring boot REST API that handles HTTP POST Request -> create new resource
    // @PostMappinf and @RequestBody
    // @PostMapping is used for mapping Http Post Request
    // @RequestBody internally uses Spring provided HttpMessageConverter to convert JSON into Java object
    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    // Spring boot REST API that handles HTTP PUT Request - updating existing resource
    @PutMapping("students/{id}/update")
    public Student updateStudent(@RequestBody Student student,@PathVariable("id") int studentId ){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //Spring boot REST API that handles HTTP delete - deleting existing resources
    @DeleteMapping("students/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return "Student deleted successfully";

    }

    //
    @GetMapping("student/getStudentResponseEntity")
    public ResponseEntity<Student> getStudentResponseEntity(){
        // obje döndüğümüzde format json oluyor.
        Student student = new Student(1,"Yigit","Sayin");
        //return new ResponseEntity<>(student,HttpStatus.OK);
        //return ResponseEntity.ok(student);
        //postman header
        return ResponseEntity.ok().header("custom-header","yigit").body(student);
    }

    //http://localhost:8080/students/getStudentsResponseEntity
    @GetMapping("/students/getStudentsResponseEntity")
    public ResponseEntity<List<Student>> getStudentsResponseEntity(){
        List<Student> students = new ArrayList<Student>();
        students.add(new Student(1,"Yigit","Sayin"));
        students.add(new Student(2,"Yigit","Sayin"));
        students.add(new Student(3,"Yigit","Sayin"));
        students.add(new Student(4,"Yigit","Sayin"));
        return ResponseEntity.ok(students);
    }


    //http://localhost:8080/students/1/yig/say
    @GetMapping("students/studentPathVariableAllVariableResponseEntity/{id}/{firstName}/{lastName}")
    public ResponseEntity<Student> studentPathVariableAllVariableResponseEntity(@PathVariable("id") int studentId,
                                                  @PathVariable String firstName,
                                                  @PathVariable String lastName) {
        if (studentId == 0) {
            throw new StudentIDZeroException("Input cannot be zero.");
        } else {
            Student student = new Student(studentId, firstName, lastName);
            return ResponseEntity.ok(student);

        }



    }
    //http://localhost:8080/students/query/studentRequestVariableAll?id=1&firstName=yig&lastName=yig
    @GetMapping("students/query/studentRequestVariableAllResponseEntity")
        public ResponseEntity<Student> studentRequestVariableAllResponseEntity(@RequestParam int id, @RequestParam("firstName") String first,@RequestParam("lastName") String last){
             Student student = new Student(id,first,last);
             return ResponseEntity.ok(student);
        }

    //We don't need to use @HttpStatus annotation ResponseEntity handle in the return
    @PostMapping("students/createStudentResponseEntity")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudentResponseEntity(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("students/{id}/updateStudentResponseEntity")
    public ResponseEntity<Student> updateStudentResponseEntity(@RequestBody Student student,@PathVariable("id") int studentId ){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("students/{id}/deleteStudentResponseEntity")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    // HttpStatus.NO_CONTENT (204): Sunucu isteği başarılı bir şekilde işlediğini belirtir ancak yanıt gövdesi boştur. Gövdeye yazılan bir veri olsa bile, kullanıcıya gönderilmez.
    //HttpStatus.CREATED (201): Sunucu isteği başarılı bir şekilde işlediğini ve yeni bir kaynak oluşturduğunu belirtir. Yanıt gövdesinde "selamlar" gibi bir içerik gönderilebilir.
    public ResponseEntity<String> deleteStudentResponseEntity(@PathVariable("id") int studentId){
        System.out.println(studentId);
        //return new ResponseEntity<>("selamlar", HttpStatus.NO_CONTENT);
        //return new ResponseEntity<>("selamlar", HttpStatus.CREATED);
        return ResponseEntity.ok("deleted successfully");

    }




}


