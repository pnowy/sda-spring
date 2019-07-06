package pl.sda.jp.miniblogw16.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.jp.miniblogw16.user.EditUserForm;
import pl.sda.jp.miniblogw16.user.UserEntity;
import pl.sda.jp.miniblogw16.user.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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
        return "admin/showUsers";
    }

    // /admin/user/2
//    @GetMapping("/user/{id}/{country}") -> @PathVariable("id") Long userId,
    @GetMapping("/user") // /admin/user?id=2
    public String getUserForEdit(@RequestParam Long id, Model model) {
        log.info("Get user to edit with id={}", id);

        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new EntityNotFoundException();
        }
        EditUserForm editUserForm = EditUserForm.create(optionalUser.get());
        model.addAttribute("editUserForm", editUserForm);
        return "admin/editUser";
    }
}
