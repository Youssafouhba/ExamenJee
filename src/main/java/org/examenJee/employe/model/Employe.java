package org.examenJee.employe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.examenJee.project.model.Project;
import org.examenJee.rolesAndPrivileges.model.Role;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "employe_projects",
            joinColumns = @JoinColumn(
                    name = "emloye_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "project_id", referencedColumnName = "id"))
    @JsonBackReference
    private Collection<Project> projects;
}
