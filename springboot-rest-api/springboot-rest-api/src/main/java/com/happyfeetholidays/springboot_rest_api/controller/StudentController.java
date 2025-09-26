package com.happyfeetholidays.springboot_rest_api.controller;

import com.happyfeetholidays.springboot_rest_api.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    //ResponseEntity implementation
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(
                1,
                "ramprasath",
                "palanichamy"
        );
        //return  new ResponseEntity<>(student,HttpStatus.OK);
        //return ResponseEntity.ok(student);
        //custom Header
        return ResponseEntity.ok()
                .header("custom-header:","ramprasath")
                .body(student);
    }

    //loaclhost:8080/students
    @GetMapping()
    public ResponseEntity<List<Student>> getStudents(){

            List<Student> studentList = new ArrayList<Student>();
        studentList.add(new Student(1,"ramprsath","palanichamy"));
        studentList.add(new Student(2,"ram","palani"));
        studentList.add(new Student(3,"prsath","chamy"));
        studentList.add(new Student(4,"ram","prasath"));
        return  ResponseEntity.ok(studentList);


    }
    //Spring boot REst Api using path variable
    //{id} URI Template variable


    @GetMapping("{id}/{firstName}/{lastName}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id")  int studentId,
                                       @PathVariable("firstName") String firstName,
                                       @PathVariable("lastName") String lastName){
        Student student = new Student(studentId, firstName,lastName);
        return ResponseEntity.ok(student);
    }

    //Spring boot REst Api using request Param
    //http://localhost:8080/students/query?id=2&firstName=ram&lastName=prasath

    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam("id")  int studentId,
                                       @RequestParam("firstName") String firstName,
                                       @RequestParam("lastName") String lastName){
        Student student = new Student(studentId, firstName,lastName);
        return ResponseEntity.ok(student);
    }

    //Spring boot REst Api handles HTTP POST Request
    //@PostMapping and @RequestBody
    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody  Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }


    //Spring boot REst Api handles HTTP PUT Request -update the existing resource
    //
    //@PostMapping and @RequestBody
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody  Student student ,@PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }


    //Spring boot REst Api handles HTTP DELETE Request -Delete the existing resource
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int  studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student Delete Successfully.");
    }


}
