package pl.sda.jp.miniblogw16.exampletests;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.verification.VerificationMode;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sda.jp.miniblogw16.api.AuthService;
import pl.sda.jp.miniblogw16.api.DbAuthService;

public class MockTest {

    @Test
    public void shouldCheckAuthService() {
        PasswordEncoder passwordEncoderMock = Mockito.mock(PasswordEncoder.class);
//        Mockito.when(passwordEncoderMock.encode(Mockito.anyString()))
//                .thenReturn("asf");
        Mockito.when(passwordEncoderMock.encode(Mockito.anyString()))
                .thenAnswer(new Answer<String>() {
                    @Override
                    public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                        String firstArg = invocationOnMock.getArgument(0);
                        System.out.println("first arg:" + firstArg);
                        if (firstArg.equalsIgnoreCase("admin")) {
                            return "nimda";
                        }
                        return firstArg;
                    }
                });
        AuthService authService = new DbAuthService(passwordEncoderMock);

        System.out.println(authService.auth("admin"));
        Mockito.verify(passwordEncoderMock, Mockito.times(1))
                .encode(Mockito.anyString());
    }
}
