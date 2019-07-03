package pl.sda.jp.miniblogw16.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sda.jp.miniblogw16.user.UserService;

import javax.sql.DataSource;

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
//                .antMatchers("/assets/**").permitAll()
//                .antMatchers("/").permitAll()
                .antMatchers("/").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/login").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/admin/users").hasRole("ADMIN")
//                .antMatchers("/").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
//                .antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/post/**").hasAnyAuthority("ROLE_USER")
//                .antMatchers("/h2/**").permitAll()
                .anyRequest().permitAll()
                    //.hasAuthority("ROLE_ADMIN")
//                .antMatchers("/post/*").hasAnyRole("USER", "ADMIN")
//                .anyRequest().permitAll()
            .and()
                .csrf().disable()
                .headers().frameOptions().disable()
            .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/loginBySpring")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/").and()
                .logout()
                    .logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN");
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
