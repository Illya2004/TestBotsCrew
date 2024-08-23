package org.kolis1on.taskbotscrew.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.kolis1on.taskbotscrew.dto.DepartmentDTO;
import org.kolis1on.taskbotscrew.dto.LectorDTO;
import org.kolis1on.taskbotscrew.entity.Department;
import org.kolis1on.taskbotscrew.entity.Lector;
import org.kolis1on.taskbotscrew.enums.Degree;
import org.kolis1on.taskbotscrew.exception.DepartmentNameIsNotCorrectException;
import org.kolis1on.taskbotscrew.repository.DepartmentRepository;
import org.kolis1on.taskbotscrew.repository.LectorRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final LectorService lectorService;

    @PostConstruct
    public void init() {
        LectorDTO lectorDTO1 = LectorDTO.builder()
                .name("Oksana Shevchenko")
                .degree(Degree.ASSISTANT)
                .salary(3000.0)
                .build();

        LectorDTO lectorDTO2 = LectorDTO.builder()
                .name("Anna Zaitseva")
                .degree(Degree.ASSOCIATE_PROFESSOR)
                .salary(3500.0)
                .build();


        LectorDTO lectorDTO3 = LectorDTO.builder()
                .name("Illia Oboronov")
                .degree(Degree.PROFESSOR)
                .salary(3030.0)
                .build();

        Lector lector1 = lectorService.createLector(lectorDTO1);
        Lector lector2 = lectorService.createLector(lectorDTO2);
        Lector lector3 = lectorService.createLector(lectorDTO3);

        List<Lector> lectors1 = Arrays.asList(lector1, lector2);

        List<Lector> lectors2 = Arrays.asList(lector2, lector3);

        DepartmentDTO departmentDTO1 = DepartmentDTO.builder()
                .name("Computer Science")
                .head(lector1)
                .lectors(lectors1)
                .build();

        DepartmentDTO departmentDTO2 = DepartmentDTO.builder()
                .name("Mathematics")
                .head(lector2)
                .lectors(lectors2)
                .build();

        createDepartment(departmentDTO1);
        createDepartment(departmentDTO2);
    }

    public Department createDepartment(DepartmentDTO dto){
        Department department = Department.builder()
                .name(dto.getName())
                .head(dto.getHead())
                .lectors(dto.getLectors())
                .build();

        return departmentRepository.save(department);

    }

    public String getHeadOfDepartment(String departmentName){
        Department department = getDepartmentByName(departmentName);

        return String.format("Head of %s department is %s\n",
                departmentName, department.getHead().getName());
    }

    public String getDepartmentStatistic(String departmentName){
        Department department = getDepartmentByName(departmentName);

        long assistantsCount = department.getLectors().stream().
                filter(lector -> lector.getDegree().equals(Degree.ASSISTANT))
                .count();

        long associateProfessorsCount = department.getLectors().stream().
                filter(lector -> lector.getDegree().equals(Degree.ASSOCIATE_PROFESSOR))
                .count();

        long professorCount = department.getLectors().stream().
                filter(lector -> lector.getDegree().equals(Degree.PROFESSOR))
                .count();

        return String.format("assistans - %s\n" +
                             "associate professors - %s\n" +
                             "professors - %s\n",
                assistantsCount, associateProfessorsCount, professorCount);
    }

    public String getAverageSalaryOfDepartment(String departmentName){
        Department department = getDepartmentByName(departmentName);

        OptionalDouble averageSalary = department.getLectors().stream()
                .mapToDouble(Lector::getSalary)
                .average();

        return String.format("The average salary of %s is %s\n ",
               departmentName, averageSalary.orElse(0.0));
    }

    public String getEmployeeCountOfDepartment(String departmentName){
        Department department = getDepartmentByName(departmentName);

        Long employeeCount = (long) department.getLectors().size();

        return String.valueOf(employeeCount);
    }



    private Department getDepartmentByName(String departmentName){
        return departmentRepository.findByName(departmentName).
                orElseThrow(() -> new DepartmentNameIsNotCorrectException
                        ("Department name is not correct, or not exist"));
    }

}
