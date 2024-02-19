package com.example.tourism.controller;

import com.example.tourism.model.TouristAttraction;
import com.example.tourism.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristController {
    private TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }


    @GetMapping("/add")
    public String showAddAttractionForm(Model model) {
        TouristAttraction defaultAttraction = new TouristAttraction("name","description", 0);
        model.addAttribute("touristAttraction",defaultAttraction);
        return "addAttraction";
    }
    @PostMapping("/add")
    public String addTouristAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.addTouristAttraction(touristAttraction);
        return "redirect:/attractions/showAll";
    }


    @GetMapping("/showAll")
public String getAttractions (Model model){
        List<TouristAttraction> attractions = touristService.getAttractions();
        model.addAttribute("attractionList",attractions);
        return "showAttractions";
    }



    @PostMapping ("/delete/{name}")
    public String deleteTouristAttraction(@PathVariable String name) {
        touristService.deleteTouristAttraction(name);
        return "redirect:/attractions/showAll";
    }

    @PostMapping("/update")
    public ResponseEntity<TouristAttraction> putTouristAttraction(@RequestBody TouristAttraction touristAttraction) {
        touristService.putTouristAttraction(touristAttraction);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{name}")
    public ResponseEntity<TouristAttraction> getAttractionByName(@PathVariable String name) {
        TouristAttraction specificAttraction = touristService.getAttractionByName(name);
        return new ResponseEntity<>(specificAttraction,HttpStatus.OK);

    }


}
