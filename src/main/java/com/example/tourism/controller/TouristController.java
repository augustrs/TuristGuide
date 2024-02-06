package com.example.tourism.controller;


import com.example.tourism.model.TouristAttraction;
import com.example.tourism.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/attractions")
public class TouristController {
    private TouristService touristService = new TouristService();


    @PostMapping("/attractions/add")
    public ResponseEntity<Void> addTouristAttraction(@RequestBody TouristAttraction touristAttraction) {
        touristService.addTouristAttraction(touristAttraction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("attractions/{name}")
    public ResponseEntity<Void> deleteTouristAttraction(@RequestBody TouristAttraction touristAttraction) {
        touristService.deleteTouristAttraction(touristAttraction);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
