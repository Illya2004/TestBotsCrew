package org.kolis1on.taskbotscrew.entity;

import jakarta.persistence.*;
import lombok.*;
import org.kolis1on.taskbotscrew.enums.Degree;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lectors")
@Builder
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated
    private Degree degree;

    private double salary;

}
