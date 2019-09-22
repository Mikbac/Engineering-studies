package com.Controllers;

import com.Entities.Car;
import com.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/car-management/")
public class CarController {

    private CarRepository carRepository;

    @Autowired
    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Car> allv() {
        return carRepository.findAll();
    }

    @RequestMapping(value = "/car", method = RequestMethod.POST)
    public ResponseEntity<Car> addCars(@RequestBody @Valid @NotNull Car car) {
        carRepository.save(car);
        return ResponseEntity.ok().body(car);
    }

    @RequestMapping(value = "/car/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> editCars(@RequestBody @Valid @NotNull Car car, @PathVariable("id") int id) {
        if (carRepository.findOne(id) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            car.setCarId(id);
            carRepository.save(car);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/car/{id}", method = RequestMethod.DELETE)
    public RedirectView deleteCars(@PathVariable("id") int id) {
        carRepository.delete(id);
        return new RedirectView("/cars", true);
    }

    @RequestMapping(value = "/car/amount", method = RequestMethod.GET)
    public Integer getCarsSize() {
        return carRepository.countCarsById();
    }

}