package pl.sda.jp.miniblogw16.exampletests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.jp.miniblogw16.user.UserEntity;
import pl.sda.jp.miniblogw16.user.UserRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
public class ExampleJpaTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldSaveUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setLastName("kowal");
        userEntity.setEmail("kowal@gmail.com");
        userEntity.setPassword("abc");

        UserEntity savedEntity = userRepository.save(userEntity);

        assert userRepository.findAll().size() > 0;
    }
}
