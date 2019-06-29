package pl.sda.jp.miniblogw16;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @GetMapping("/register")
    public String showRegisterForm() {


        return "registerForm";
    }

    @PostMapping("/register")
    public String handleRegisterForm(
            @ModelAttribute String firstName
    ) {
        System.out.println(firstName);

        return "registerForm";
    }
}
