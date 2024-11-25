package com.posco.carmanagement.posco.domain.car;

import com.posco.carmanagement.posco.domain.car.Car;
import com.posco.carmanagement.posco.domain.car.CarRepository;
import com.posco.carmanagement.posco.domain.car.CreateCarCommand;
import com.posco.carmanagement.posco.domain.car.DeleteCarCommand;
import com.posco.carmanagement.posco.domain.car.UpdateCarCommand;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car create(CreateCarCommand command) {
        Car car = new Car();
        car.setResourceId(command.getResourceId());
        car.setCarrierNo(command.getCarrierNo());
        car.setWorksCode(command.getWorksCode());
        car.setCompanyCode(command.getCompanyCode());
        car.setCarType(command.getCarType());
        car.setCarModel(command.getCarModel());
        car.setUsage(command.getUsage());
        car.setFuelFlag(command.getFuelFlag());
        car.setPartCompanyCd(command.getPartCompanyCd());
        car.setOwnerTypeCode(command.getOwnerTypeCode());
        return carRepository.save(car);
    }

    public Car update(Long id, UpdateCarCommand command) {
        Car existing = findById(id);
        if (command.getCarType() != null) {
            existing.setCarType(command.getCarType());
        }
        if (command.getCarModel() != null) {
            existing.setCarModel(command.getCarModel());
        }
        if (command.getUsage() != null) {
            existing.setUsage(command.getUsage());
        }
        if (command.getFuelFlag() != null) {
            existing.setFuelFlag(command.getFuelFlag());
        }
        if (command.getDriverEmpNo() != null) {
            existing.setDriverEmpNo(command.getDriverEmpNo());
        }
        if (command.getUserName() != null) {
            existing.setUserName(command.getUserName());
        }
        if (command.getDriverIds() != null) {
            existing.setDriverIds(command.getDriverIds());
        }
        return carRepository.save(existing);
    }

    public void delete(Long id) {
        Car car = findById(id);
        carRepository.delete(car);
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car findById(Long id) {
        return carRepository
            .findById(id)
            .orElseThrow(() ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Car not found"
                )
            );
    }
    //// mybatis
}
