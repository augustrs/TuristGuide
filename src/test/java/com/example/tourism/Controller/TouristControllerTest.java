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
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TouristController.class)
public class TouristControllerTest {

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

    @Test
    void testShowCreateAttractionForm() throws Exception {
        mockMvc.perform(get("/attractions/add"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("tags", Matchers.notNullValue()))
                .andExpect(model().attribute("tags", Matchers.empty()))
                .andExpect(model().attributeExists("touristAttraction"))
                .andExpect(view().name("addAttraction"));
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


