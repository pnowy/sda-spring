package pl.sda.jp.miniblogw16.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.jp.miniblogw16.user.EditUserForm;
import pl.sda.jp.miniblogw16.user.UserEntity;
import pl.sda.jp.miniblogw16.user.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

// @Controller
@RestController // add automatically @ResponseBody and @RequestBody
@RequestMapping("/api")
@AllArgsConstructor
public class UserRestController {

    private  final UserRepository userRepository;

    @GetMapping("/users")
    public Collection<EditUserForm> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(EditUserForm::create)
                .collect(Collectors.toList());
    }

    @GetMapping("/user")// /admin/user?id=2
    public EditUserForm getUserById(@RequestParam Long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new EntityNotFoundException();
        }
        return EditUserForm.create(optionalUser.get());
    }

    @GetMapping("/users/{id}")// /api/users/2
    public EditUserForm getUserByIdv2(@PathVariable Long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new EntityNotFoundException();
        }
        return EditUserForm.create(optionalUser.get());
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {

    }

}
