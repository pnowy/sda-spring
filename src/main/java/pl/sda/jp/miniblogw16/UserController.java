package pl.sda.jp.miniblogw16;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.jp.miniblogw16.user.RegisterForm;
import pl.sda.jp.miniblogw16.user.UserEntity;
import pl.sda.jp.miniblogw16.user.UserRepository;

@Controller
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String showRegisterForm() {


        return "registerForm";
    }

    @PostMapping("/register")
    public String handleRegisterForm(
            @ModelAttribute RegisterForm registerForm
    ) {
        UserEntity user = new UserEntity();
        user.setFirstName(registerForm.getFirstName());
        user.setLastName(registerForm.getLastName());
        user.setEmail(registerForm.getEmail());
        user.setPassword(registerForm.getPassword());

        userRepository.save(user);

        return "registerForm";
    }
}
