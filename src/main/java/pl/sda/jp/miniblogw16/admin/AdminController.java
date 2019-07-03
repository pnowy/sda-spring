package pl.sda.jp.miniblogw16.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sda.jp.miniblogw16.user.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/showUsers";
    }

    @GetMapping("/user")
    public String editUser(@RequestParam Long id, Model model) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        EditUserForm editUserForm = new EditUserForm();
        editUserForm.setId(user.getId());
        editUserForm.setFirstName(user.getFirstName());
        editUserForm.setLastName(user.getLastName());
        editUserForm.setEmail(user.getEmail());
        editUserForm.setRoles(user.getRoles().stream().map(RoleEntity::getRoleName).collect(Collectors.toSet()));
        model.addAttribute("user", editUserForm);
//        model.addAttribute("roles", roleRepository.findAll());
        return "admin/editUser";
    }

    @PostMapping("/user")
    public String editUserSave(@ModelAttribute("user") @Valid EditUserForm user,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
//            model.addAttribute("id", user.getId());
            model.addAttribute("user", user);
            return "/admin/editUser";
        }
        System.out.println(user);
        return "redirect:/admin/user?id="+user.getId();
    }
}
