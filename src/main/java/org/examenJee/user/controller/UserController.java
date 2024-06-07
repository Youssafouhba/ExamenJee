package org.examenJee.user.controller;



import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.examenJee.employe.model.Employe;
import org.examenJee.employe.service.EmployeService;
import org.examenJee.jwt.util.JwtUtil;
import org.examenJee.user.service.AdminService;
import org.examenJee.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@AllArgsConstructor
@RestController
public class UserController {
  private final EmployeService employeService;
  private final UserService userService;
  private final AdminService adminService;

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @GetMapping("/employees")
  public String listEmployees(Model model) {
    List<Employe> employees = employeService.getAllEmployees();
    logger.info("Recovered {} employees", employees.size());
    System.out.println(employees);
    model.addAttribute("employees", employees);
    return "employees";
  }

  @GetMapping("/form")
  public String showForm(Model model) {
    model.addAttribute("employe", new Employe());
    return "form";
  }

  @PostMapping("/form")
  public String processForm(@ModelAttribute("employe") Employe employe, BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "form";
    }
    return "redirect:/success";
  }


}
