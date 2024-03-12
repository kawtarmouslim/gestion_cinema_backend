package org.example.gestion_cinema;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


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
