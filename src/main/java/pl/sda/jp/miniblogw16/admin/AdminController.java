package pl.sda.jp.miniblogw16.admin;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.jp.miniblogw16.user.UserEntity;
import pl.sda.jp.miniblogw16.user.UserRepository;

import java.util.List;

@Controller
//@Slf4j
@RequestMapping("/admin")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<UserEntity> users = userRepository.findAll();
        model.addAttribute("users", users);
        // model.addAttribute("cokolwiek", );
        return "admin/showUsers";
    }

    // /admin/user/2
//    @GetMapping("/user/{id}/{country}") -> @PathVariable("id") Long userId,
    @GetMapping("/user") // /admin/user?id=2
    public String getUserForEdit(@RequestParam Long id) {
        log.info("Get user to edit with id={}", id);
        // todo put edit logic here
            // 1. find user
            // 2. put user data to edit user form // EditUserForm
            // 3. return edit form
        return "admin/editUser";
    }
}
