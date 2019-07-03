package pl.sda.jp.miniblogw16;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String showHomePage() {
        return "homePage";
    }

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }

}
