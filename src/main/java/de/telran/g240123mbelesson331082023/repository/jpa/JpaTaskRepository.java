package de.telran.g240123mbelesson331082023.repository.jpa;

import de.telran.g240123mbelesson331082023.domain.entity.jpa.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTaskRepository extends JpaRepository<Task, Integer> {



}
