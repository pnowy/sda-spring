package pl.sda.jp.miniblogw16.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

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

    public void addRole(RoleEntity roleEntity){
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(roleEntity);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("email='" + email + "'")
                .toString();
    }
}
