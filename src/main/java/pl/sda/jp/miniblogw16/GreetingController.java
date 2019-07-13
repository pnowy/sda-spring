package pl.sda.jp.miniblogw16;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.jp.miniblogw16.user.EditUserForm;

@RestController
@AllArgsConstructor
public class GreetingController {

    private final GreetingService service;

    @GetMapping("/greeting")
    public EditUserForm greeting() {
        return service.greet();
    }

}
