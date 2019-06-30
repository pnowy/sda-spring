package pl.sda.jp.miniblogw16;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.jp.miniblogw16.user.EmailAlreadyExistsException;
import pl.sda.jp.miniblogw16.user.RegisterForm;
import pl.sda.jp.miniblogw16.user.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "registerForm";
    }

    @PostMapping("/register")
    public String handleRegisterForm(
            @ModelAttribute @Valid RegisterForm registerForm,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
//            model.addAttribute("registerForm", registerForm);
            return "registerForm";
        }

        try {
            userService.registerUser(registerForm);
        } catch (EmailAlreadyExistsException e) {
            bindingResult.rejectValue("email", "email-duplicate", "duplikat emaila");
            return "registerForm";
        }
        return "redirect:/";
    }
}
