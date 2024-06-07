package org.examenJee.config;

import jakarta.transaction.Transactional;

import org.examenJee.employe.model.Employe;
import org.examenJee.employe.repository.EmployeRepository;
import org.examenJee.project.model.Project;
import org.examenJee.project.repository.ProjectRepository;
import org.examenJee.rolesAndPrivileges.model.Privilege;
import org.examenJee.rolesAndPrivileges.model.Role;
import org.examenJee.rolesAndPrivileges.services.PrivilegeService;
import org.examenJee.rolesAndPrivileges.services.RoleService;
import org.examenJee.user.model.Admin;
import org.examenJee.user.repository.AdminRepository;
import org.examenJee.user.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.random;


@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = true;

    private final UserRepository userRepository;

    private final EmployeRepository employeRepository;

    private final ProjectRepository projectRepository;

    private final RoleService roleService;

    private final PrivilegeService privilegeService;

    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    public SetupDataLoader(UserRepository userRepository, EmployeRepository employeRepository, ProjectRepository projectRepository, RoleService roleService, PrivilegeService privilegeService, AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.employeRepository = employeRepository;
        this.projectRepository = projectRepository;
        this.roleService = roleService;
        this.privilegeService = privilegeService;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        Privilege readPrivilege
                = privilegeService.createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = privilegeService.createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        Privilege editPrivilege
                = privilegeService.createPrivilegeIfNotFound("EDIT_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege,editPrivilege);

        roleService.createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        roleService.createRoleIfNotFound("ROLE_AGENT",Arrays.asList(readPrivilege,writePrivilege));
        roleService.createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
        Role adminRole = roleService.findByName("ROLE_ADMIN");
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(Arrays.asList(adminRole));
        adminRepository.save(admin);

/*
        for (int i = 0; i < 4; i++) {
            Employe employe = new Employe();
            employe.setName("employe"+i);
            employe.setEmail("employe"+i+"@gmail.com");
            employeRepository.save(employe);
        }


        for (int i = 0; i < 4; i++) {
            Project project = new Project();
            project.setName("Project"+i);
            project.setBudget(random()*1000);
            projectRepository.save(project);
        }
 */

        alreadySetup = true;
    }

}
