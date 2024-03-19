package com.example.tourism;

import com.example.tourism.controller.TouristController;
import com.example.tourism.model.TouristAttraction;
import com.example.tourism.repository.TouristRepository;
import com.example.tourism.service.TouristService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TuristGuideApplicationTests {

    @Autowired
    private TouristController controller;

    @Autowired
    private TouristService service;
    @Autowired
    private TouristRepository repository;


/*
    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
        assertThat(service).isNotNull();
        assertThat(repository).isNotNull();
    }
    
 */





}
