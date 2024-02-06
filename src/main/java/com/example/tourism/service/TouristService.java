package com.example.tourism.service;


import com.example.tourism.model.TouristAttraction;
import com.example.tourism.repository.TouristRepository;
import org.springframework.stereotype.Service;

@Service
public class TouristService {
    private TouristRepository touristRepository = new TouristRepository();


    public void addTouristAttraction(TouristAttraction attraction) {
        touristRepository.addTouristAttraction(attraction);
    }

    public void deleteTouristAttraction(String name) {
        touristRepository.deleteTouristAttractionFromList(name);
    }
}
