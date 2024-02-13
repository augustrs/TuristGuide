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


    @GetMapping("/showAll")
public String getAttractions (Model model){
        List<TouristAttraction> attractions = touristService.getAttractions();
        model.addAttribute("attractionList",attractions);
        return "showAttractions";
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addTouristAttraction(@RequestBody TouristAttraction touristAttraction) {
        touristService.addTouristAttraction(touristAttraction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteTouristAttraction(@PathVariable String name) {
        touristService.deleteTouristAttraction(name);
        return new ResponseEntity<>(HttpStatus.OK);
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
