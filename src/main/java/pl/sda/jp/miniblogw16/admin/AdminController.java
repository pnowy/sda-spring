package pl.sda.jp.miniblogw16.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/users")
    public String showUsers(){
        return "admin/showUsers";
    }
}
