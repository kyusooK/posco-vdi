package com.posco.carmanagement.posco.service;

import com.posco.carmanagement.posco.domain.driver.CreateDriverCommand;
import com.posco.carmanagement.posco.domain.driver.DeleteDriverCommand;
import com.posco.carmanagement.posco.domain.driver.Driver;
import com.posco.carmanagement.posco.domain.driver.DriverService;
import com.posco.carmanagement.posco.domain.driver.UpdateDriverCommand;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping(path = "/drivers")
    public ResponseEntity<List<Driver>> findAll() {
        return ResponseEntity.ok(driverService.findAll());
    }

    @PostMapping(path = "/drivers")
    public ResponseEntity<Driver> create(
        @Valid @RequestBody CreateDriverCommand command
    ) {
        return ResponseEntity.ok(driverService.create(command));
    }

    @PatchMapping(path = "/drivers/{driverEmpNo}")
    public ResponseEntity<Driver> update(
        @PathVariable String driverEmpNo,
        @Valid @RequestBody UpdateDriverCommand command
    ) {
        return ResponseEntity.ok(driverService.update(driverEmpNo, command));
    }

    @DeleteMapping(path = "/drivers/{driverEmpNo}")
    public ResponseEntity<Void> delete(@PathVariable String driverEmpNo) {
        driverService.delete(driverEmpNo);
        return ResponseEntity.noContent().build();
    }
}
