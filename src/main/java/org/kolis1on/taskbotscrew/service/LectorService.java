package org.kolis1on.taskbotscrew.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.kolis1on.taskbotscrew.dto.DepartmentDTO;
import org.kolis1on.taskbotscrew.dto.LectorDTO;
import org.kolis1on.taskbotscrew.entity.Lector;
import org.kolis1on.taskbotscrew.enums.Degree;
import org.kolis1on.taskbotscrew.repository.LectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectorService {

    private final LectorRepository lectorRepository;

    public Lector createLector(LectorDTO dto){

        Lector lector = Lector.builder()
                .name(dto.getName())
                .degree(dto.getDegree())
                .salary(dto.getSalary())
                .build();

        return lectorRepository.save(lector);
    }

    public String globalSearchByTemplate(String template){
        String lowerCaseTemplate = template.toLowerCase();

        List<String> matchingLectors = lectorRepository.findAll().stream()
                .map(Lector::getName)
                .filter(name -> name.toLowerCase().contains(lowerCaseTemplate))
                .toList();

        if (matchingLectors.isEmpty()) {
            return String.format("No lectors found matching the template '%s'.", template);
        } else {
            return String.format("%s", matchingLectors);
        }
    }
}
