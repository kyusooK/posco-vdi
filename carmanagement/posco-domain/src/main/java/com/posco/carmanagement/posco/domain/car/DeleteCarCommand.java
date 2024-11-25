package com.posco.carmanagement.posco.domain.car;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class DeleteCarCommand {

    private Long resourceId;
}
