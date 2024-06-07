package org.examenJee.rolesAndPrivileges.repositories;

import org.examenJee.rolesAndPrivileges.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
    Privilege findByName(String name);
}
