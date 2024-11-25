package com.posco.carmanagement.posco.domain.driver;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class DeleteDriverCommand {

    private String driverEmpNo;
}
