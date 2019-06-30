package pl.sda.jp.miniblogw16.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 100)
    private String firstName;
    private String lastName;
    @Column(length = 150, unique = true)
    private String email;

    private String password;

    @ManyToMany
    @JoinTable(name = "user_role")
    private Set<RoleEntity> roles;
}
