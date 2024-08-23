package org.kolis1on.taskbotscrew.dto;

import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import org.kolis1on.taskbotscrew.enums.Degree;

@Data
@Builder
public class LectorDTO {

    private String name;

    private Degree degree;

    private double salary;
}
