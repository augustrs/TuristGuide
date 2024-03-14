package com.example.tourism.repository;


import com.example.tourism.model.TouristAttraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {



    @Value("${spring.datasource.url}")
    private String db_url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String pwd;



    public void testMethod()  {
 try (Connection con = DriverManager.getConnection("turistguidesql.mysql.database.azure.com","turistguidesql","Joakimerdum1")) {
    String SQL = "INSERT INTO DEPT (DEPTNO,DNAME,LOC) values (20,'DET','VIRKER');";
    PreparedStatement pstmt = con.prepareStatement(SQL);
    pstmt.executeUpdate();
        } catch (SQLException e) {
     throw new RuntimeException(e);
 }
    }

    List<TouristAttraction> attractions = new ArrayList<>(List.of(
            new TouristAttraction("Det runde tårn", "Et højt rundt tårn", List.of("Bygning")),
            new TouristAttraction("Den blå planet", "En blå planet", List.of("fisk")),
            new TouristAttraction("Københavns Zoologiske have", "Et sted fyldt med dyr", List.of("Dyr")),
            new TouristAttraction("Operahuset", "Et koncerthus", List.of("Musik")),
            new TouristAttraction("Den lille havfrue", "en havfrue", List.of("Statue"))));


    public TouristAttraction createTouristAttraction(String name, String description, List<String> tags) {
        TouristAttraction touristAttraction = new TouristAttraction(name,description, tags);
        return touristAttraction;
    }
    public void addTouristAttraction(TouristAttraction touristAttraction) {
        attractions.add(touristAttraction);
    }
    public void deleteTouristAttractionFromList(String name) {
        int i =0;
        TouristAttraction foundAttraction;
        while (i<attractions.size()) {
            if (name.equals(attractions.get(i).getName())) {
                foundAttraction = attractions.get(i);
                attractions.remove(foundAttraction);


            }
            i++;
        }




        }
        // TODO add for multiple searchresults


    public List<TouristAttraction> getAttractions() {
        return attractions;
    }
    public TouristAttraction getAttractionByName(String name) {
        int i =0;
        TouristAttraction foundAttraction=null;
        while (i<attractions.size()) {
            if (name.equals(attractions.get(i).getName())) {
                return attractions.get(i);

            }
            i++;
        }
        return null;
    }

    public List<String> getTagsForAttraction(String name) {
        TouristAttraction attraction = getAttractionByName(name);
        if (attraction != null) {
            return attraction.getTags();
        } else
            return null;

    }

    public List<String> getTags() {
        List<String> tags = new ArrayList<>();
        for (TouristAttraction attraction : attractions) {
            tags.addAll(attraction.getTags());
        }
        return tags;
    }

    public void changeTouristAttraction(TouristAttraction updateAttraction) {
        //Overskriv eksisterende message
        for (TouristAttraction touristAttraction : attractions) {
            if (touristAttraction.getName().equals(updateAttraction.getName())) {
                touristAttraction.setDescription(updateAttraction.getDescription());
                touristAttraction.setTags(updateAttraction.getTags());
            }


        }

        // find attraction i attractions
        /*
        int i=0;
        while (i < attractions.size()) {
            if (touristAttraction.getId()==attractions.get(i).getId()){
                attractions.set(i,touristAttraction);
            return touristAttraction;
            }
            i++;

         */
       // }return null; // ikke fundet

    }



}
