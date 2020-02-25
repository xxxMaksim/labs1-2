package by.bsuir.lab1.controller;

import by.bsuir.lab1.model.Student;
import by.bsuir.lab1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/**/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ModelAndView getStudents() {
        ModelAndView modelAndView = new ModelAndView("studentPage");
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.getModel().put("students", studentService.getStudents());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView saveStudent(@RequestParam String firstName, @RequestParam String secondName,
                                    @RequestParam String faculty, @RequestParam int course,
                                    @RequestParam String cardNumber) {
        studentService.saveStudent(firstName, secondName, faculty, course, cardNumber);
        return new ModelAndView("redirect:students");
    }


    @GetMapping("/delete")
    public ModelAndView deleteStudent(@RequestParam String cardNumber) {
        HttpStatus httpStatus;
        if (studentService.deleteStudent(cardNumber) == 0) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            httpStatus = HttpStatus.OK;
        }
        ModelAndView modelAndView = new ModelAndView("studentPage");
        modelAndView.setStatus(httpStatus);
        return modelAndView;
    }

    @GetMapping("/update")
    public ModelAndView updateStudent(@RequestParam String firstName, @RequestParam String secondName,
                                      @RequestParam String faculty, @RequestParam int course,
                                      @RequestParam String cardNumber) {
        HttpStatus httpStatus;
        if (studentService.updateStudent(new Student()
                .setCardNumber(cardNumber)
                .setCourse(course)
                .setFaculty(faculty)
                .setFirstName(firstName)
                .setSecondName(secondName)) == 0){
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            httpStatus = HttpStatus.OK;
        }
        ModelAndView modelAndView = new ModelAndView("studentPage");
        modelAndView.setStatus(httpStatus);
        return modelAndView;
    }


    public StudentController setStudentService(StudentService studentService) {
        this.studentService = studentService;
        return this;
    }
}
