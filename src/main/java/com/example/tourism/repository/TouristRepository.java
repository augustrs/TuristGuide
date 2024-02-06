package com.example.tourism.repository;


import com.example.tourism.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    List<TouristAttraction> attractions = new ArrayList<>();


    public TouristAttraction createTouristAttraction(String name, String description) {
        TouristAttraction touristAttraction = new TouristAttraction(name,description);
        return touristAttraction;
    }
    public void addTouristAttraction(TouristAttraction touristAttraction) {
        attractions.add(touristAttraction);
    }
    public void deleteTouristAttractionFromList(String touristAttractionNameString) {

        if (searchForTouristAttraction(touristAttractionNameString).size() == 1) {
            attractions.remove(searchForTouristAttraction(touristAttractionNameString).get(0));

        }
        // TODO add for multiple searchresults
    }
    public List<TouristAttraction> searchForTouristAttraction(String attractionSearchKey) {
        List<TouristAttraction> searchResult = new ArrayList<>();
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().toLowerCase().contains(attractionSearchKey.toLowerCase())) {
                searchResult.add(attraction);
            }
        }
        return searchResult;
    }
}
