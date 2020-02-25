package by.bsuir.lab1.controller;

import by.bsuir.lab1.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/**/reverseString")
public class StringController {

    @Autowired
    public UtilService utilService;

    @GetMapping
    public ModelAndView getReversedString(@RequestParam(defaultValue = "hello") String string) {
        ModelAndView modelAndView = new ModelAndView("stringPage");
        modelAndView.getModel().put("reversedString", utilService.getReversedString(string));
        return modelAndView;
    }

}
