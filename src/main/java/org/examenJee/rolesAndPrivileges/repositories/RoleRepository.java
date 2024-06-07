package org.examenJee.rolesAndPrivileges.repositories;



import org.examenJee.rolesAndPrivileges.model.Role;
import org.examenJee.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleAdmin);

    Role findRoleByUsersIn(Collection<User> users);

}

