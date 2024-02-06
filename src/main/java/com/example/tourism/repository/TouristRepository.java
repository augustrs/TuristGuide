package com.example.tourism.repository;


import com.example.tourism.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    List<TouristAttraction> attractions = new ArrayList<>(List.of(new TouristAttraction("Det runde tårn","Et højt rundt tårn")));


    public TouristAttraction createTouristAttraction(String name, String description) {
        TouristAttraction touristAttraction = new TouristAttraction(name,description);
        return touristAttraction;
    }
    public void addTouristAttraction(TouristAttraction touristAttraction) {
        attractions.add(touristAttraction);
    }
    public void deleteTouristAttractionFromList(TouristAttraction touristAttraction) {

            attractions.remove(touristAttraction);

        }
        // TODO add for multiple searchresults

    public List<TouristAttraction> searchForTouristAttraction(String attractionSearchKey) {
        List<TouristAttraction> searchResult = new ArrayList<>();
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().toLowerCase().contains(attractionSearchKey.toLowerCase())) {
                searchResult.add(attraction);
            }
        }
        return searchResult;
    }
    public List<TouristAttraction> getAttractions() {
        return attractions;
    }
}
