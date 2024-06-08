package com.fresco.carData.repository;

import com.fresco.carData.model.CarData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDataRepository extends JpaRepository<CarData,Integer> {


}
