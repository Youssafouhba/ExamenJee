package org.examenJee.rolesAndPrivileges.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@NoArgsConstructor
@Data
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    @JsonBackReference
    private Collection<Role> roles;

    public Privilege(String name){
        this.name = name;
    }
}