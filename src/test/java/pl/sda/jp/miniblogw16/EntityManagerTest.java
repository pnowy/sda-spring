package pl.sda.jp.miniblogw16;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EntityManagerTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void shouldHaveEntityManager() {
        assert entityManager != null;
    }
}
