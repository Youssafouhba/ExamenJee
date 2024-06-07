package org.examenJee.user.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.examenJee.rolesAndPrivileges.model.Role;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  private String nom;
  private String prenom;
  @Column(unique = true)
  private String username;
  private String password;

  @Column(name = "first_login", columnDefinition = "boolean default true")
  private boolean firstLogin = true;
  @ManyToMany
  @JsonIgnore
  @JoinTable(
          name = "users_roles",
          joinColumns = @JoinColumn(
                  name = "user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(
                  name = "role_id", referencedColumnName = "id"))
  @JsonBackReference
  private Collection<Role> roles;
}
