package pl.sda.jp.miniblogw16;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.jp.miniblogw16.service.RoleService;
import pl.sda.jp.miniblogw16.user.RoleEntity;
import pl.sda.jp.miniblogw16.user.RoleRepository;

import javax.persistence.EntityManager;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EntityManagerTest {

//    UserTransaction utx = entityManager.getTransaction();
//    try {
//        utx.begin();
//        businessLogic();
//        utx.commit();
//    } catch(Exception ex) {
//        utx.rollback();
//        throw ex;
//    }

//    @Transactional
//    public void businessLogic() {
//        ... use entity manager inside a transaction ...
//    }

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Test
    public void shouldHaveEntityManager() {
        assert entityManager != null;
    }

    @Test
    @Transactional
    @Rollback(false)
    public void shouldSaveRoleByEntityManager() {
        RoleEntity roleEditor = new RoleEntity("ROLE_EDITOR");
        entityManager.persist(roleEditor);
    }

    @Test
//    @Transactional
//    @Rollback(false)
    public void shouldSaveRoleByRepository() {
        RoleEntity roleEditor = new RoleEntity("ROLE_EDITOR_v2");
        roleRepository.save(roleEditor);
    }

    @Test
    public void shouldRenameRoleService() {
        roleService.editRoleWithPropagation("ROLE_EDITOR", "ROLE_EDITOR_v5");
//        RoleEntity roleEditor = new RoleEntity("ROLE_EDITOR_v2");
//        roleRepository.save(roleEditor);
    }
}
