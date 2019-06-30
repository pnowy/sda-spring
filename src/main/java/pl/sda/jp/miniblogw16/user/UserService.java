package pl.sda.jp.miniblogw16.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    public static final String ROLE_USER = "ROLE_USER";
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
        user.setPassword(registerForm.getPassword());

//        Set<RoleEntity> rolesSet = new HashSet<>();
//        rolesSet.add(defaultRole);
//        user.setRoles(rolesSet);

        user.addRole(defaultRole);

        userRepository.save(user);

    }
}
