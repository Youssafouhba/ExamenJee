package org.examenJee.jwt.services;


import lombok.AllArgsConstructor;
import org.examenJee.jwt.models.AuthenticationResponse;
import org.examenJee.jwt.models.UserPrincipal;
import org.examenJee.jwt.util.JwtUtil;
import org.examenJee.rolesAndPrivileges.model.Role;
import org.examenJee.rolesAndPrivileges.repositories.RoleRepository;
import org.examenJee.user.model.User;
import org.examenJee.user.repository.UserRepository;
import org.examenJee.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


@Service
@AllArgsConstructor
public class AuthenticationService implements UserDetailsService {

  public final UserService userService;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final ModelMapper mapper;
  private final JwtUtil jwtUtil;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    var userEntity = userService.getUserByUsername(email);
    return new UserPrincipal(userEntity);
  }


  public ResponseEntity<?> authenticate(String username, String password)
          throws NoSuchAlgorithmException {
    if (username.isEmpty() || password.isEmpty()) {
      throw new BadCredentialsException("Unauthorized");
    }

    var userEntity = userService.getUserByUsername(username);

    if (userEntity == null) {
      return ResponseEntity.badRequest().body("{\"message\":\"There are no account with these credentials\"}");
    }

    if (!passwordEncoder.matches(password, userEntity.getPassword())) {
      return ResponseEntity.badRequest().body("{\"message\":\"Password Incorrect !\"}");
    }

    Role role = getRole(userEntity.getId());
    UserPrincipal userDetails = new UserPrincipal(userEntity);
    var jwt = jwtUtil.generateToken(userDetails);
    return ResponseEntity.ok().body( new AuthenticationResponse(role,jwt,userEntity.getId()));
  }


  public Role getRole(Long id){
    return this.roleRepository.findRoleByUsersIn(Arrays.asList(mapper.map(userService.getUserById(id), User.class)));
  }

}
