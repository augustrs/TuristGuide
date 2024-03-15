package com.example.tourism;

import com.example.tourism.repository.TouristRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class TuristGuideApplication {


    public static void main(String[] args) throws SQLException {
        SpringApplication.run(TuristGuideApplication.class, args);

    }
}
