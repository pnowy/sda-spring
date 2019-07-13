package pl.sda.jp.miniblogw16.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthServiceConfiguration {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Primary // oznaczenie beana jako głównego/podstawowego (w przypadku kilku beanów będzie domyślnie wstrzykiwany ten)
    @Bean(name = "dbAuthService") // każdy bean można oznaczać za pomocą nazwy
    public AuthService dbAuthService() {
        return new DbAuthService(passwordEncoder);
    }

    @Bean(name = "mockAuthService")
    public AuthService mockAuthService() {
        return new AuthService() {
            @Override
            public boolean auth(String password) {
                System.out.println("different auth service");
                return false;
            }
        };
    }

}
