package org.kolis1on.taskbotscrew;

import org.kolis1on.taskbotscrew.entity.Department;
import org.kolis1on.taskbotscrew.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskBotsCrewApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskBotsCrewApplication.class, args);
    }

    static{
        DepartmentService departmentService;
    }


}
