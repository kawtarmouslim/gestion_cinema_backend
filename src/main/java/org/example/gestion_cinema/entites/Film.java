package org.example.gestion_cinema.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Film implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String realisateur;
    private Date dateFilm;
    private String genre;
    private String cheminImage;
    @OneToMany(mappedBy = "film")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)// n affiche pas projection dans le rponse Json, pour ne reste pas en boucle == JsonIgnore
    @ToString.Exclude
    private Collection<Projection> projections;
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", titre='" + titre + '\'' +

                '}';
    }


}
