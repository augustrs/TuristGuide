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


    public int findHighestId() {
        try (Connection con = DriverManager.getConnection(db_url, username, pwd)) {
            String SQL = "SELECT MAX(ATTRACTIONID) FROM ATTRACTION";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int highestId = rs.getInt("MAX(ATTRACTIONID)") + 1;
            return highestId;
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
            while (rs.next()) {
                int id = rs.getInt("ATTRACTIONID");
                String name = rs.getString("ANAME");
                String description = rs.getString("DESCR");
                String location = rs.getString("LOC");
                List<String> tags = getTagsForAttraction(id);
                attractionList.add(TouristAttraction.createAttraction(id, name, description, location, tags));
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
            while (rs.next()) {
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












    public TouristAttraction getAttractionByName(String name) {
        List<TouristAttraction> attractionList = getAttractionAsObject();
        TouristAttraction foundAttraction = null;
        for (TouristAttraction attraction : attractionList) {
            if (name.equals(attraction.getName())) {
                foundAttraction = attraction;
            }

            }
        return foundAttraction;
    }


    public void updateAttraction(TouristAttraction attractionToUpdate) {
        int id = attractionToUpdate.getId();
        String name = attractionToUpdate.getName();
        String description = attractionToUpdate.getDescription();

        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
            String SQL = "UPDATE ATTRACTION set ANAME = ?, DESCR = ? WHERE ATTRACTIONID = ?";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, id);
            ps.executeUpdate();
           // System.out.println(ps.executeUpdate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAttraction(TouristAttraction touristAttraction) {
        try (Connection connection = DriverManager.getConnection(db_url, username, pwd)) {
            String SQL = "INSERT INTO ATTRACTION (ANAME, LOC, DESCR) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, touristAttraction.getName());
            ps.setString(2, touristAttraction.getLocation());
            ps.setString(3, touristAttraction.getDescription());
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            int attractionId = -1;
            if (generatedKeys.next()) {
                attractionId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating attraction failed, no ID obtained.");
            }

            List<String> tags = touristAttraction.getTags();
            if (tags != null && !tags.isEmpty()) {
                String tagSQL = "INSERT INTO ATTRACTIONTAGS (TAGID, ATTRACTIONID) VALUES (?, ?)";
                for (String tagName : tags) {
                    int tagId = findTouristTagFromListSQL(tagName);
                    if (tagId != -1) {
                        PreparedStatement tagPS = connection.prepareStatement(tagSQL);
                        tagPS.setInt(1, tagId);
                        tagPS.setInt(2, attractionId);
                        tagPS.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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


