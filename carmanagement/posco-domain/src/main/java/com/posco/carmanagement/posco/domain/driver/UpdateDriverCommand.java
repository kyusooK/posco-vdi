package com.posco.carmanagement.posco.domain.driver;

import com.posco.carmanagement.posco.domain.Photo;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateDriverCommand {

    private String retirementFlag;
    private Date retiredDate;
    private Photo image;
}
