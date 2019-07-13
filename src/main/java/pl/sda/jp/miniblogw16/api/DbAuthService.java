package pl.sda.jp.miniblogw16.api;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

//@Service
//@Profile("develop")
@AllArgsConstructor
public class DbAuthService implements AuthService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean auth(String password) {
        String encoded = passwordEncoder.encode(password);
        System.out.println("DB AUTH SERVICE: "+ encoded);
        // more complicated logic here
        return encoded.length() > 4;
    }

    @PostConstruct
    public void init() {
//        auth();
    }
}
