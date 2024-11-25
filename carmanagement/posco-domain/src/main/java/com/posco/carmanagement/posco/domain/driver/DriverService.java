package com.posco.carmanagement.posco.domain.driver;

import com.posco.carmanagement.posco.domain.driver.CreateDriverCommand;
import com.posco.carmanagement.posco.domain.driver.DeleteDriverCommand;
import com.posco.carmanagement.posco.domain.driver.Driver;
import com.posco.carmanagement.posco.domain.driver.DriverRepository;
import com.posco.carmanagement.posco.domain.driver.UpdateDriverCommand;
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
public class DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver create(CreateDriverCommand command) {
        Driver driver = new Driver();
        driver.setDriverEmpNo(command.getDriverEmpNo());
        driver.setName(command.getName());
        driver.setCompanyCode(command.getCompanyCode());
        driver.setWorksCode(command.getWorksCode());
        return driverRepository.save(driver);
    }

    public Driver update(String id, UpdateDriverCommand command) {
        Driver existing = findById(id);
        if (command.getRetirementFlag() != null) {
            existing.setRetirementFlag(command.getRetirementFlag());
        }
        if (command.getRetiredDate() != null) {
            existing.setRetiredDate(command.getRetiredDate());
        }
        if (command.getImage() != null) {
            existing.setImage(command.getImage());
        }
        return driverRepository.save(existing);
    }

    public void delete(String id) {
        Driver driver = findById(id);
        driverRepository.delete(driver);
    }

    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    public Driver findById(String id) {
        return driverRepository
            .findById(id)
            .orElseThrow(() ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Driver not found"
                )
            );
    }
    //// mybatis
}
