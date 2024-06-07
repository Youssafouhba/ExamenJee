package org.examenJee.rolesAndPrivileges.services;

import jakarta.transaction.Transactional;
import org.examenJee.rolesAndPrivileges.model.Privilege;
import org.examenJee.rolesAndPrivileges.repositories.PrivilegeRepository;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService {
    private final PrivilegeRepository privilegeRepository;

    public PrivilegeService(PrivilegeRepository privilegeRepository1) {
        this.privilegeRepository = privilegeRepository1;
    }

    @Transactional
    public Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

}
