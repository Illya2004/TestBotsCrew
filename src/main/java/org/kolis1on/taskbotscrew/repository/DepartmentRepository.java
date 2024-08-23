package org.kolis1on.taskbotscrew.repository;

import org.kolis1on.taskbotscrew.entity.Department;
import org.kolis1on.taskbotscrew.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByName (String name);
}