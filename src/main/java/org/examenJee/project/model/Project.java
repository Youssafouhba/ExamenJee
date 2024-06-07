package org.examenJee.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.examenJee.employe.model.Employe;
import org.examenJee.user.model.User;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;
    private double budget;

    @ManyToMany(mappedBy = "projects")
    @JsonManagedReference
    private Collection<Employe> employes;
}
