package pl.sda.jp.miniblogw16;

import org.springframework.stereotype.Service;
import pl.sda.jp.miniblogw16.user.EditUserForm;

@Service
public class GreetingService {

    public EditUserForm greet() {
        return new EditUserForm();
    }

}
