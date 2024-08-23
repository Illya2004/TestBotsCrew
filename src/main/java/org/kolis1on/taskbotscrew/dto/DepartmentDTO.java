package org.kolis1on.taskbotscrew.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import org.kolis1on.taskbotscrew.entity.Lector;

import java.util.List;

@Data
@Builder
public class DepartmentDTO {
    private String name;

    private Lector head;

    private List<Lector> lectors;
}
