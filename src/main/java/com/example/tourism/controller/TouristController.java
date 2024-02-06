package com.example.tourism.controller;


import com.example.tourism.model.TouristAttraction;
import com.example.tourism.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristController {
    private TouristService touristService = new TouristService();


    @GetMapping()
public ResponseEntity<List<TouristAttraction>> getAttractions (){
        List<TouristAttraction> attractions = touristService.getAttractions();
        return new ResponseEntity<>(attractions,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addTouristAttraction(@RequestBody TouristAttraction touristAttraction) {
        touristService.addTouristAttraction(touristAttraction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteTouristAttraction(@RequestBody TouristAttraction touristAttraction) {
        touristService.deleteTouristAttraction(touristAttraction);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/update")
    public ResponseEntity<TouristAttraction> putTouristAttraction(@RequestBody TouristAttraction touristAttraction) {
        touristService.putTouristAttraction(touristAttraction);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
