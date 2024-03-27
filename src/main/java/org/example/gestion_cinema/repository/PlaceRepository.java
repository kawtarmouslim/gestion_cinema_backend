package org.example.gestion_cinema.repository;


import org.example.gestion_cinema.entites.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin("*")
public interface PlaceRepository extends JpaRepository<Place,Long> {
//    @Query("SELECT p.prix FROM Place p WHERE p.id = :placeId")
//    Double findPriceById(@Param("placeId") Long placeId);
}
