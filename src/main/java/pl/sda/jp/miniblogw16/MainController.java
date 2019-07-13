package pl.sda.jp.miniblogw16;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.jp.miniblogw16.api.AuthService;
import pl.sda.jp.miniblogw16.user.EmailAlreadyExistsException;
import pl.sda.jp.miniblogw16.user.RegisterForm;
import pl.sda.jp.miniblogw16.user.UserService;

import javax.validation.Valid;

@Controller
public class MainController {

//    @Autowired
//    @Qualifier("myBeanCustomName")
//    private ObjectMapper objectMapper;

//    @Autowired
//    @Qualifier("")
//    private AuthService authService;

    @GetMapping("/")
    public String showHomePage() {
        return "homePage";
    }

}
