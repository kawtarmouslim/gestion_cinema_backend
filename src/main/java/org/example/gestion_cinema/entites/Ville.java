package org.example.gestion_cinema.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data  @NoArgsConstructor @AllArgsConstructor @ToString
public class Ville {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String name;
// @OneToMany(mappedBy = "ville")
// private Collection<Cinema> cinemas;


}
