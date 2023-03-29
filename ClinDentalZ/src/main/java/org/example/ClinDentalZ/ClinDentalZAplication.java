package org.example.ClinDentalZ;

import org.example.ClinDentalZ.bd.BD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinDentalZAplication {

    public static void main(String[] args) {
        BD.crearTablas();

        SpringApplication.run(ClinDentalZAplication.class, args);

    }
}