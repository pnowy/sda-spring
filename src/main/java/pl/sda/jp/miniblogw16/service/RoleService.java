package pl.sda.jp.miniblogw16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.jp.miniblogw16.user.RoleEntity;
import pl.sda.jp.miniblogw16.user.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleBackupService backupService;

    @Transactional
    public void editRole(String oldName, String newName) {
        editRoleName(oldName, newName);
    }

    @Transactional
    public void editRoleWithPropagation(String oldName, String newName) {
        backupService.editRole(oldName, newName);
        editRole(oldName, newName + "_LOCAL");
        if (true) {
            throw new IllegalStateException("Unexpected exception!");
        }
    }

    private void editRoleName(String oldName, String newName) {
        RoleEntity role = roleRepository.findByRoleName(oldName).get();
        role.setRoleName(newName);
    }

}
