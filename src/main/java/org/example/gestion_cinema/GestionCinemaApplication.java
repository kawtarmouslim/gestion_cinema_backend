package org.example.gestion_cinema;

import org.example.gestion_cinema.dtos.TicketDto;
import org.example.gestion_cinema.entites.Ticket;
import org.hibernate.criterion.ProjectionList;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
@SpringBootApplication
public class GestionCinemaApplication  {




    public static void main(String[] args) {


        SpringApplication.run(GestionCinemaApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {


        return new ModelMapper();
    }
    }


