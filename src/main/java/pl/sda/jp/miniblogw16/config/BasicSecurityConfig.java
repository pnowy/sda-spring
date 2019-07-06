package pl.sda.jp.miniblogw16.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.sda.jp.miniblogw16.config.Roles.ADMIN;
import static pl.sda.jp.miniblogw16.config.Roles.USER;

@Configuration
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/").hasAnyRole(ADMIN.getRoleName(), USER.getRoleName())
                .antMatchers("/admin/**").hasRole(ADMIN.getRoleName())
                    //.hasAuthority("ROLE_ADMIN")
                .antMatchers("/post/**").hasAnyRole(ADMIN.getRoleName(), USER.getRoleName())
                .anyRequest().permitAll()
            .and()
                .csrf().disable()
                .headers().frameOptions().disable()
            .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/loginBySpring")
                .defaultSuccessUrl("/")
                .failureUrl("/login?status=error")
            .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin@localhost.com")
                .password(passwordEncoder.encode("admin"))
                .roles(ADMIN.getRoleName());

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT u.email, u.password, 1 " +
                        "FROM user_entity u " +
                        "WHERE u.email = ?")
                .authoritiesByUsernameQuery("SELECT u.email, r.role_name " +
                        "FROM user_entity u " +
                        "LEFT JOIN user_role ur ON u.id = ur.user_entity_id " +
                        "LEFT JOIN role_entity r ON ur.roles_id = r.id " +
                        "WHERE u.email = ?")
                .passwordEncoder(passwordEncoder);
    }
}
