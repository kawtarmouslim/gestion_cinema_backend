package org.example.gestion_cinema.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Place implements Serializable {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    @ManyToOne
     private  Salle salle;

    @OneToMany(mappedBy = "place")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    private Collection<Ticket>tickets;

}
