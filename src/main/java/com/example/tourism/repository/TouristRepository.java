package com.example.tourism.repository;


import com.example.tourism.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    List<TouristAttraction> attractions = new ArrayList<>(List.of(new TouristAttraction("Det runde tårn","Et højt rundt tårn",1),new TouristAttraction("Den lille havfrue","en havfrue",2)));


    public TouristAttraction createTouristAttraction(String name, String description) {
        TouristAttraction touristAttraction = new TouristAttraction(name,description,attractions.size()+1);
        return touristAttraction;
    }
    public void addTouristAttraction(TouristAttraction touristAttraction) {
        attractions.add(touristAttraction);
    }
    public void deleteTouristAttractionFromList(TouristAttraction touristAttraction) {

            attractions.remove(touristAttraction);

        }
        // TODO add for multiple searchresults


    public List<TouristAttraction> getAttractions() {
        return attractions;
    }
    public TouristAttraction getAttractionByName(String name) {
        int i =0;
        while (i<attractions.size()) {
            if (name.equals(attractions.get(i).getName())) {
                return attractions.get(i);
            }
            i++;
        }
        return null;
    }

    public TouristAttraction changeTouristAttraction(TouristAttraction touristAttraction) {
        //Overskriv eksisterende message


        // find attraction i attractions
        int i=0;
        while (i < attractions.size()) {
            if (touristAttraction.getId()==attractions.get(i).getId()){
                attractions.set(i,touristAttraction);
            return touristAttraction;
            }
            i++;
        }
        return null; // ikke fundet

    }

}
