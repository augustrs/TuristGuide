package com.example.tourism.service;


import com.example.tourism.model.TouristAttraction;
import com.example.tourism.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
    TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public List<TouristAttraction> getAttractionFromSQL() {
        return touristRepository.getAttractionAsObject();
    }

    public void updateAttraction(TouristAttraction attractionToUpdate) {
        touristRepository.updateAttraction(attractionToUpdate);
    }


    public void deleteTouristAttractionSQL(String name) {
        touristRepository.deleteTouristAttractionFromListSQL(name);
    }


    //public TouristAttraction putTouristAttraction(TouristAttraction touristAttraction) {
    //  TouristAttraction returnTouristAttraction = touristRepository.changeTouristAttraction(touristAttraction);
    // return returnTouristAttraction;
    //}

    public TouristAttraction getAttractionByName(String name) {
        return touristRepository.getAttractionByName(name);
    }

    public List<String> getTagsForAttraction(String name) {
        return touristRepository.getTags(name);
    }


}

