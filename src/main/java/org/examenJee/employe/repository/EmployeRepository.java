package org.examenJee.employe.repository;

import org.examenJee.employe.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe,Long > {
}
