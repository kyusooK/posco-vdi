package com.posco.carmanagement.posco.domain.driver;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateDriverCommand {

    private String driverEmpNo;
    private String name;
    private String companyCode;
    private String worksCode;
}
