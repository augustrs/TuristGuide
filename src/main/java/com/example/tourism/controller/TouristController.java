package com.example.tourism.controller;

import com.example.tourism.model.TouristAttraction;
import com.example.tourism.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristController {
    private TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping("/update/{name}")
    public String showUpdateAttractionForm(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        model.addAttribute("touristAttraction", attraction);
        return "updateAttraction";
    }


    @PostMapping("/update")
    public String updateTouristAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.updateAttraction(touristAttraction);
        return "redirect:/attractions/showAll";
    }



    @GetMapping("/add")
    public String showAddAttractionForm(Model model) {
        int id = touristService.getHighestId();
        TouristAttraction defaultAttraction = new TouristAttraction(id, "name", "description", "location", List.of("tags"));
        model.addAttribute("touristAttraction", defaultAttraction);
        model.addAttribute("tags", Collections.emptyList());
        return "addAttraction";
    }
    @PostMapping("/add")
    public String addTouristAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.addTouristAttraction(touristAttraction);
        return "redirect:/attractions/showAll";
    }





        // TouristAttraction defaultAttraction = new TouristAttraction("name","description", List.of("tags"));
        //model.addAttribute("touristAttraction",defaultAttraction);
        //return "addAttraction";

    /*
    @PostMapping("/add")
    public String addTouristAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.addTouristAttraction(touristAttraction);
        return "redirect:/attractions/showAll";
    }

     */


    @GetMapping("/showAll")
public String getAttractions (Model model){
        List<TouristAttraction> attractions = touristService.getAttractionFromSQL();
        model.addAttribute("attractionList",attractions);
        return "showAttractions";
    }

    @GetMapping("/tags/{name}")
    public String showAttractionTags(@PathVariable String name, Model model) {

        List<String> tags = touristService.getTagsForAttraction(name);
        model.addAttribute("tags", tags);
        return "tags";
    }

    @PostMapping ("/delete/{name}")
    public String deleteTouristAttraction(@PathVariable String name) {
        touristService.deleteTouristAttractionSQL(name);
        return "redirect:/attractions/showAll";
    }


    @GetMapping("/{name}")
    public ResponseEntity<TouristAttraction> getAttractionByName(@PathVariable String name) {
        TouristAttraction specificAttraction = touristService.getAttractionByName(name);
        return new ResponseEntity<>(specificAttraction,HttpStatus.OK);

    }


}