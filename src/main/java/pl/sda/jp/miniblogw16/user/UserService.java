package pl.sda.jp.miniblogw16.user;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserService {
    public static final String ROLE_USER = "ROLE_USER";
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(RegisterForm registerForm) throws EmailAlreadyExistsException {

        if (userRepository.existsByEmail(registerForm.getEmail())) {
            throw new EmailAlreadyExistsException("Duplicate email");
        }
//        RoleEntity role_user = null;
//        Optional<RoleEntity> defaultRole = roleRepository.findByRoleName(ROLE_USER);
//        if (! defaultRole.isPresent()){
//            role_user = roleRepository.save(new RoleEntity(ROLE_USER));
//        }
//        //role_user..

        RoleEntity defaultRole = roleRepository.findByRoleName(ROLE_USER)
                .orElseGet(() -> roleRepository.save(new RoleEntity(ROLE_USER)));
        // TAK NIE ROBIMY !!!
        //.orElse(roleRepository.save(new RoleEntity(ROLE_USER)));


        UserEntity user = new UserEntity();
        user.setFirstName(registerForm.getFirstName());
        user.setLastName(registerForm.getLastName());
        user.setEmail(registerForm.getEmail());
        //user.setPassword(registerForm.getPassword());
        user.setPassword(passwordEncoder.encode(registerForm.getPassword()));

//        Set<RoleEntity> rolesSet = new HashSet<>();
//        rolesSet.add(defaultRole);
//        user.setRoles(rolesSet);

        user.addRole(defaultRole);

        userRepository.save(user);

    }

    @PostConstruct
    void init() {
//        System.out.println("User service after init!");
    }
}
