package pl.sda.jp.miniblogw16;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.jp.miniblogw16.user.RoleEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JdbcTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void jdbcTemplate() {
        assert jdbcTemplate != null;
        jdbcTemplate.query("SELECT id, role_name FROM ROLE_ENTITY", new RowMapper<RoleEntity>() {
            @Override
            public RoleEntity mapRow(ResultSet resultSet, int i) throws SQLException {
                long roleId = resultSet.getLong("id");
                String roleName = resultSet.getString("role_name");
                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setId(roleId);
                roleEntity.setRoleName(roleName);
                return roleEntity;
            }
        });
    }
}
