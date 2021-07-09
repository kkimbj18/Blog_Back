package kr.kro.blog.myself.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/security")
public class SecurityController {

    @RequestMapping("/")
    public String main(Model model) {
        model.addAttribute("data", "Hello, Spring from IntelliJ!");
        return "hello";
    }

    @RequestMapping("/generate/token")
    public String genToken(@RequestParam(value="subject") String subject, Model model) {
        model.addAttribute("data", subject);

        return "hello";
    }
}
