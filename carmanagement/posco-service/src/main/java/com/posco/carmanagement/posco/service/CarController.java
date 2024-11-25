package com.posco.carmanagement.posco.service;

import com.posco.carmanagement.posco.domain.car.Car;
import com.posco.carmanagement.posco.domain.car.CarService;
import com.posco.carmanagement.posco.domain.car.CreateCarCommand;
import com.posco.carmanagement.posco.domain.car.DeleteCarCommand;
import com.posco.carmanagement.posco.domain.car.UpdateCarCommand;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(path = "/cars")
    public ResponseEntity<List<Car>> findAll() {
        return ResponseEntity.ok(carService.findAll());
    }

    @PostMapping(path = "/cars")
    public ResponseEntity<Car> create(
        @Valid @RequestBody CreateCarCommand command
    ) {
        return ResponseEntity.ok(carService.create(command));
    }

    @PatchMapping(path = "/cars/{resourceId}")
    public ResponseEntity<Car> update(
        @PathVariable Long resourceId,
        @Valid @RequestBody UpdateCarCommand command
    ) {
        return ResponseEntity.ok(carService.update(resourceId, command));
    }

    @DeleteMapping(path = "/cars/{resourceId}")
    public ResponseEntity<Void> delete(@PathVariable Long resourceId) {
        carService.delete(resourceId);
        return ResponseEntity.noContent().build();
    }
}
