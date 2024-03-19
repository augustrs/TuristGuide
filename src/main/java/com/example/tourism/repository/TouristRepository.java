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


    public void testMethod() {
        try (Connection con = DriverManager.getConnection(db_url, username, pwd)) {
            String SQL = "INSERT INTO DEPT (DEPTNO,DNAME,LOC) VALUES (17,'NU','VIRKER')";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TouristAttraction> getAttractionAsObject() {
        List<TouristAttraction> attractionList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
            String SQL = "SELECT * FROM ATTRACTION";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("ATTRACTIONID");
                String name = rs.getString("ANAME");
                String description = rs.getString("DESCR");
                String location = rs.getString("LOC");
                List<String> tags = getTagsForAttraction(id);
                attractionList.add(TouristAttraction.createAttraction(id, name, description, location,tags));
            }
            return attractionList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public List<String> getTags(String name) {
        List<TouristAttraction> attractionList = getAttractionAsObject();
        for (TouristAttraction touristAttraction : attractionList) {
            if (touristAttraction.getName().equalsIgnoreCase(name)) {
                return touristAttraction.getTags();

            }
        }
        return null;
    }






    public List<String> getTagsForAttraction(int attractionId) {
        //int attractionId = findTouristAttractionFromListSQL(id);
        List<String> tags = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
            String SQL = "SELECT tags.TAGNAME FROM ATTRACTIONTAGS at INNER JOIN TAGS tags ON at.TAGID = tags.TAGID WHERE at.ATTRACTIONID = ?";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setInt(1, attractionId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                tags.add(rs.getString("TAGNAME"));
            }
            return tags;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public int findTouristTagFromListSQL(String name) {

        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
            String SQL = "SELECT * FROM TAGS WHERE TAGNAME = ?";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            rs.next();

            int id = rs.getInt("TAGID");
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public int findTouristAttractionFromListSQL(String name) {

        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
            String SQL = "SELECT * FROM ATTRACTION WHERE ANAME = ?";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            rs.next();

            int id = rs.getInt("ATTRACTIONID");
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void deleteTouristAttractionFromListSQL(String name) {
        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
            int id = findTouristAttractionFromListSQL(name);
            if (id != -1) {
                String deleteAttractionTagsSQL = "DELETE FROM ATTRACTIONTAGS WHERE ATTRACTIONID = ?";
                PreparedStatement psAttractionTags = connection.prepareStatement(deleteAttractionTagsSQL);
                psAttractionTags.setInt(1, id);
                psAttractionTags.executeUpdate();

                String deleteAttractionSQL = "DELETE FROM ATTRACTION WHERE ATTRACTIONID = ?";
                PreparedStatement psAttraction = connection.prepareStatement(deleteAttractionSQL);
                psAttraction.setInt(1, id);
                psAttraction.executeUpdate();
            }
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
        TouristAttraction touristAttraction = new TouristAttraction(name, description, tags);
        return touristAttraction;
    }



    public void addTouristAttraction(TouristAttraction touristAttraction) {
        attractions.add(touristAttraction);
    }

    public void deleteTouristAttractionFromList(String name) {
        int i = 0;
        TouristAttraction foundAttraction;
        while (i < attractions.size()) {
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
        int i = 0;
        TouristAttraction foundAttraction = null;
        while (i < attractions.size()) {
            if (name.equals(attractions.get(i).getName())) {
                return attractions.get(i);

            }
            i++;
        }
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