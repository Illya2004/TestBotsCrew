package org.kolis1on.taskbotscrew.repository;

import org.kolis1on.taskbotscrew.entity.Department;
import org.kolis1on.taskbotscrew.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {

    Optional<Lector> findByName (String name);
}
