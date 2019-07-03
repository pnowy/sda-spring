package pl.sda.jp.miniblogw16;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sda.jp.miniblogw16.user.EmailAlreadyExistsException;
import pl.sda.jp.miniblogw16.user.RegisterForm;
import pl.sda.jp.miniblogw16.user.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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

    // TODO: For each new user add "ROLE_USER" automatically
    @PostMapping("/register")
    public String handleRegisterForm(
            @ModelAttribute @Valid RegisterForm registerForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
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
        redirectAttributes.addFlashAttribute("msg", "Dzięki za rejestrację!");
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "loginForm";
    }

    @GetMapping("/rest")
    @ResponseBody
    public Map<String, Object> restApi() {
        Map<String, Object> map = new HashMap<>();
        map.put("testValue", 1);
        map.put("value2", "wartość");
        return map;
    }
}
