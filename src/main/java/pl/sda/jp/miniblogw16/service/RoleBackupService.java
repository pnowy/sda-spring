package pl.sda.jp.miniblogw16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.jp.miniblogw16.user.RoleEntity;
import pl.sda.jp.miniblogw16.user.RoleRepository;

@Service
public class RoleBackupService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void editRole(String oldName, String newName) {
        editRoleName(oldName, newName);
    }

    private void editRoleName(String oldName, String newName) {
        RoleEntity role = roleRepository.findByRoleName(oldName).get();
        role.setRoleName(newName);
    }

}
