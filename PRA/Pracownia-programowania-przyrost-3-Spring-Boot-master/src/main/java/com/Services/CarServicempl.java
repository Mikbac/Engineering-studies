package com.Services;

import com.Entities.Car;
import com.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CarServicempl implements CarService {


    @Autowired
    private CarRepository carRepository;

    @Override
    public Iterable<Car> listAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarsById(Integer id) {
        return carRepository.findOne(id);
    }

    @Override
    public Car saveCars(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteCars(Integer id) {
        carRepository.delete(id);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        return carRepository.checkIfExist(id) > 0;
    }


    @Override
    public Integer getNumberOfCars() {
        return carRepository.countCarsById();
    }

}
