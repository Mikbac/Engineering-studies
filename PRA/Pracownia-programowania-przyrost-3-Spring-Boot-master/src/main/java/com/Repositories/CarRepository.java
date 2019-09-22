package com.Repositories;

import com.Entities.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarRepository extends CrudRepository<Car, Integer>, PagingAndSortingRepository<Car, Integer> {

    Car findByCarId(Integer carId);

    @Query("select count(*) from Car p where p.carId = ?1")
    Integer checkIfExist(Integer carId);

    @Query("select count(*) from Car")
    Integer countCarsById();

}
