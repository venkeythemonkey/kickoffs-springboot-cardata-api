package com.fresco.carData.controller;


import com.fresco.carData.model.CarData;
import com.fresco.carData.service.CarDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
public class CarDataController {


    @Autowired
    private CarDataService carDataService;

    //CREATE
    //Add the car data
    @PostMapping("/carData/add")
    private Object postCarData(@RequestBody CarData carData) {
        return carDataService.postCarData(carData);
    }

    //READ
    //List of all id's
    @GetMapping("/carData/list")
    private Object getCarData() {
       return carDataService.getCarData();
    }

    //UPDATE
    //updating price
    @PatchMapping("/carData/update/{id}")
    private ResponseEntity<Object> updatePrice(@PathVariable int id, @RequestBody CarData carData) {
        return carDataService.updatePrice(id, carData);
    }

    //DELETE
    //delete through id
    @DeleteMapping("/carData/delete/{id}")
    public ResponseEntity<Object> deleteCarDataById(@PathVariable int id) {
        return carDataService.deleteCarDataById(id);
    }
}
