package pl.sda.jp.miniblogw16.exampletests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.jp.miniblogw16.api.Address;
import pl.sda.jp.miniblogw16.api.FamilyMember;
import pl.sda.jp.miniblogw16.api.FamilyMemberType;
import pl.sda.jp.miniblogw16.user.*;

import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FullContextTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userRepository.deleteAllInBatch();
    }

    @Test
    public void shouldCreateUser() throws EmailAlreadyExistsException {
        RegisterForm registerForm = new RegisterForm();
        registerForm.setEmail("kowalski@gmail.com");
        registerForm.setFirstName("Jan");
        registerForm.setLastName("Kowalski");
        registerForm.setPassword("admin");

        userService.registerUser(registerForm);

        int numberOfUsers = userRepository.findAll().size();
        System.out.println(numberOfUsers);
//        assert numberOfUsers > 0;
//        Assertions.assertThat(numberOfUsers).isEqualTo(1);
//        Optional<UserEntity> userEntity = Optional.ofNullable(new UserEntity());
//        Assertions.assertThat(userEntity).isPresent();
    }


    @Test
    public void shouldShowUserCount() {
        int numberOfUsers = userRepository.findAll().size();
        System.out.println(numberOfUsers);
    }
}
