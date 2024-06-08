package com.fresco.carData.service;

import com.fresco.carData.model.CarData;
import com.fresco.carData.repository.CarDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarDataService {

    @Autowired
    private CarDataRepository carDataRepository;


    //POST
    public ResponseEntity<CarData> postCarData(CarData carData) {
        CarData savedCarData =  carDataRepository.save(carData);

        return ResponseEntity.status(201).body(savedCarData);
    }

    //GET
    public ResponseEntity<Object> getCarData() {
       List<CarData> carDataList = carDataRepository.findAll();
        if(carDataList.isEmpty()){
            return ResponseEntity.status(400).body("No Data Available");
        }

       return ResponseEntity.status(200).body(carDataList);

    }

    //PATCH
    public ResponseEntity<Object> updatePrice(int id, CarData carData)
    {
        Optional<CarData> toBeUpdated = carDataRepository.findById(id);

        if(toBeUpdated.isEmpty()){
            return ResponseEntity.status(400).build();
        }
        toBeUpdated.get().setPrice(carData.getPrice());
        CarData savedCarDate = carDataRepository.save(toBeUpdated.get());
        return ResponseEntity.status(200).body(savedCarDate);


    }

    //DELETE
    public ResponseEntity<Object> deleteCarDataById(int id)
    {
        Optional<CarData> toBeDeleted = carDataRepository.findById(id);
        if(toBeDeleted.isEmpty()){
            return ResponseEntity.status(400).build();
        }
        carDataRepository.deleteById(id);

       return ResponseEntity.status(200).build();
    }

}
