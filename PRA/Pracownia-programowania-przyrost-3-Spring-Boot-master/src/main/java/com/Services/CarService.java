package com.Services;

import com.Entities.Car;

public interface CarService {


    Iterable<Car> listAllCars();

    Car getCarsById(Integer id);

    Car saveCars(Car car);

    void deleteCars(Integer id);

    Boolean checkIfExist(Integer id);

    Integer getNumberOfCars();

}
