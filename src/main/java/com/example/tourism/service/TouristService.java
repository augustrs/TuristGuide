package com.example.tourism.service;


import com.example.tourism.model.TouristAttraction;
import com.example.tourism.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
    private TouristRepository touristRepository = new TouristRepository();


    public void addTouristAttraction(TouristAttraction attraction) {
        touristRepository.addTouristAttraction(attraction);
    }

    public void deleteTouristAttraction(TouristAttraction touristAttraction) {
        touristRepository.deleteTouristAttractionFromList(touristAttraction);
    }

    public List<TouristAttraction> getAttractions() {
        return touristRepository.getAttractions();
    }

    public TouristAttraction putTouristAttraction(TouristAttraction touristAttraction) {
        TouristAttraction returnTouristAttraction = touristRepository.changeTouristAttraction(touristAttraction);
        return returnTouristAttraction;
    }
}


