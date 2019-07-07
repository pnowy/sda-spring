package pl.sda.jp.miniblogw16;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.jp.miniblogw16.user.UserRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MiniblogW16ApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void contextLoads() {
        
        System.out.println(userRepository.findAll().size());

    }

}
