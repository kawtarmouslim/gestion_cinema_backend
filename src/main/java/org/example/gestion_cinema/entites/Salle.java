package org.example.gestion_cinema.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor

public class Salle implements Serializable {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int nombrePlace;
    @OneToMany(mappedBy = "salle")
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Projection> projections;
    public String toString() {
        return "Salle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nombrePlace=" + nombrePlace +
                '}';
    }
}
