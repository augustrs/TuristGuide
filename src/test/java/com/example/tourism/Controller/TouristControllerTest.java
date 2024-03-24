package com.example.tourism.Controller;

import com.example.tourism.controller.TouristController;
import com.example.tourism.model.TouristAttraction;
import com.example.tourism.service.TouristService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TouristController.class)
public class TouristControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TouristService touristService;

    @MockBean
    private TouristAttraction attraction;


    @Test
    void testShowUpdateAttractionForm() throws Exception {
        TouristAttraction attraction = new TouristAttraction(1, "name", "desciption", "Location", List.of("tag1", "tag2"));
        attraction.setName("Det runde tårn");
        when(touristService.getAttractionByName("Det runde tårn")).thenReturn(attraction);
        mockMvc.perform(get("/attractions/update/{name}", "Det runde tårn"))
                .andExpect(status().isOk())
                .andExpect(view().name("updateAttraction"))
                .andExpect(model().attribute("touristAttraction", attraction));
    }

    @Test
    void testUpdateTouristAttraction() throws Exception {
        TouristAttraction attraction = new TouristAttraction(1, "Det runde tårn", "description", "Location", List.of("tag1", "tag2"));
        when(touristService.getAttractionByName("Det runde tårn")).thenReturn(attraction);
        mockMvc.perform(post("/attractions/update")
                        .param("id", "1")
                        .param("name", "Det runde tårn")
                        .param("description", "updated description")
                        .param("location", "updated location")
                        .param("tags[0]", "tag1")
                        .param("tags[1]", "tag2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/attractions/showAll"));
    }

    @Test
    void testShowAddAttractionForm() throws Exception {
        mockMvc.perform(get("/attractions/add"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("tags", Matchers.notNullValue()))
                .andExpect(model().attribute("tags", Matchers.empty()))
                .andExpect(model().attributeExists("touristAttraction"))
                .andExpect(view().name("addAttraction"));
    }

    @Test
    void testAddTouristAttraction() throws Exception {
        TouristAttraction attraction = new TouristAttraction(1, "Name", "Description", "Location", List.of("tag1", "tag2"));
        when(touristService.getAttractionByName("Name")).thenReturn(null);
        mockMvc.perform(post("/attractions/add")
                        .param("id", "1")
                        .param("name", "Name")
                        .param("description", "Description")
                        .param("location", "Location")
                        .param("tags[0]", "tag1")
                        .param("tags[1]", "tag2"))
                .andExpect(status().is3xxRedirection())

                .andExpect(redirectedUrl("/attractions/showAll"));
    }

    @Test
    void testShowAllAttractions() throws Exception {
    List<TouristAttraction> attractions = new ArrayList<>();
        when(touristService.getAttractionFromSQL()).thenReturn(attractions);
        mockMvc.perform(get("/attractions/showAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("showAttractions"));
    }
    @Test
    void testShowAttractionTags() throws Exception {
        String attractionName = "Det runde tårn";
        List<String> tags = Arrays.asList("Bygning");
        when(touristService.getTagsForAttraction(attractionName)).thenReturn(tags);
        mockMvc.perform(get("/attractions/tags/{name}", attractionName))
                .andExpect(status().isOk())
                .andExpect(view().name("tags"))
                .andExpect(model().attribute("tags", tags));

    }

    @Test
    void testDeleteTouristAttraction() throws Exception {
        String attractionName = "Test Attraction";

        mockMvc.perform(post("/attractions/delete/{name}", attractionName))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/attractions/showAll"));

        verify(touristService).deleteTouristAttractionSQL(attractionName);
    }

}





/*

    private TouristAttraction touristAttraction = new TouristAttraction("Den blå planet", " aquarim", List.of("fisk"));


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TouristService touristService;



    @Test
    void testShowAllAttractions() throws Exception {
        mockMvc.perform(get("/attractions/showAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("showAttractions"));
    }



    @Test
    void testShowAttractionTags() throws Exception {
        mockMvc.perform(get("/attractions/tags/Den blå planet"))
                .andExpect(status().isOk())
                .andExpect(view().name("tags"))
                .andExpect(content().string(containsString("Den blå planet tags")));
    }


}
   /*
    @Test
    void testSaveTouristAttraction() throws Exception {
        mockMvc.perform(post("/attractions/save")
                        .param("name", "Den blå planet")  // Assuming you're passing form parameters
                        .param("description", "aquarium")
                        .param("tags", "fisk"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/attractions"));
    }
    */


