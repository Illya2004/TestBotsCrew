package org.kolis1on.taskbotscrew;

import lombok.RequiredArgsConstructor;
import org.kolis1on.taskbotscrew.service.DepartmentService;
import org.kolis1on.taskbotscrew.service.LectorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final DepartmentService departmentService;
    private final LectorService lectorService;


    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your command:");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            try {
                handleCommand(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void handleCommand(String input) {
        if (input.startsWith("Who is head of department ")) {
            String departmentName = input.substring("Who is head of department ".length()).trim();
            System.out.println(departmentService.getHeadOfDepartment(departmentName));
        }
        else if (input.startsWith("Show") && input.contains("statistics")) {
            String[] parts = input.split(" statistics");
            if (parts.length > 0) {
                String departmentName = parts[0].substring("Show ".length()).trim();
                System.out.println(departmentService.getDepartmentStatistic(departmentName));
            } else {
                System.out.println("Invalid format for department statistics query.");
            }
        }
        else if (input.startsWith("Show the average salary for the department ")) {
            String departmentName = input.substring("Show the average salary for the department ".length()).trim();
            System.out.println(departmentService.getAverageSalaryOfDepartment(departmentName));
        }
        else if (input.startsWith("Show count of employee for ")) {
            String departmentName = input.substring("Show count of employee for ".length()).trim();
            System.out.println(departmentService.getEmployeeCountOfDepartment(departmentName));
        }
        else if (input.startsWith("Global search by ")) {
            String template = input.substring("Global search by ".length()).trim();
            System.out.println(lectorService.globalSearchByTemplate(template));
        }
        else {
            System.out.println("Unknown command. Please try again.");
        }
    }

}
