package pl.sda.jp.miniblogw16.user;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test
    public void shouldDoSomething() {
        PasswordEncoder passwordEncoderMock = Mockito.mock(PasswordEncoder.class);
        Mockito.when(passwordEncoderMock.encode(Mockito.anyString()))
                .thenReturn("prosteHaslo");
    }
}