package org.examenJee.jwt.controller;


import lombok.AllArgsConstructor;
import org.examenJee.jwt.models.AuthenticationRequest;
import org.examenJee.jwt.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticateController {
  private final AuthenticationService authenticateService;

  @PostMapping(value = "/login")
  public ResponseEntity<?> authenticate(
    @RequestBody AuthenticationRequest loginRequest
  ) throws Exception {
    return authenticateService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
  }

}
