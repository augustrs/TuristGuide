package com.example.tourism.service;


import com.example.tourism.model.TouristAttraction;
import com.example.tourism.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
    TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository =touristRepository;
    }

    public void addTouristAttraction(TouristAttraction attraction) {
        touristRepository.addTouristAttraction(attraction);
    }

    public void deleteTouristAttraction(String name) {
        touristRepository.deleteTouristAttractionFromList(name);
    }

    public List<TouristAttraction> getAttractions() {
        return touristRepository.getAttractions();
    }

    //public TouristAttraction putTouristAttraction(TouristAttraction touristAttraction) {
    //  TouristAttraction returnTouristAttraction = touristRepository.changeTouristAttraction(touristAttraction);
    // return returnTouristAttraction;
    //}
    public void updateAttraction(TouristAttraction touristAttraction) {
        touristRepository.changeTouristAttraction(touristAttraction);
    }
    public TouristAttraction getAttractionByName(String name) {
        return touristRepository.getAttractionByName(name);
    }
    public List<String> getTagsForAttraction(String name){
        return touristRepository.getTagsForAttraction(name);
    }

    public List<String> getTags() {
        return touristRepository.getTags();
    }
}

