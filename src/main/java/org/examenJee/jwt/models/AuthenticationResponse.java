package org.examenJee.jwt.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.examenJee.rolesAndPrivileges.model.Role;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse implements Serializable {
  private Role role;
  private String token;
  private Long id;
}
