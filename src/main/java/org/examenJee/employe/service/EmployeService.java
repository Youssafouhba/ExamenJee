package org.examenJee.employe.service;

import lombok.AllArgsConstructor;
import org.examenJee.employe.model.Employe;
import org.examenJee.employe.repository.EmployeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EmployeService {
    private final EmployeRepository employeRepository;

    public List<Employe> getAllEmployees() {
        return employeRepository.findAll();
    }
}
