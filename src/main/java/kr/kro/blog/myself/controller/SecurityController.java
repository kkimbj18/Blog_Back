package kr.kro.blog.myself.controller;

import kr.kro.blog.myself.config.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    private SecurityService securityService;

//    public String main(Model model) {
//        model.addAttribute("data", "Hello, Spring from IntelliJ!");
//        return "hello";
//    }

    @RequestMapping("/generate/token")
    public Map<String, Object> genToken(@RequestParam(value="subject") String subject) {
        String token = securityService.createToken(subject, (2 * 1000 * 60));
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("result", token);

        return map;
    }

    @RequestMapping("/get/subject")
    public Map<String, Object> getSubject(@RequestParam("token") String token) {
        String subject = securityService.getSubject(token);
        Map <String, Object> map = new LinkedHashMap<String, Object>();
        map.put("result", subject);

        return map;
    }
}
