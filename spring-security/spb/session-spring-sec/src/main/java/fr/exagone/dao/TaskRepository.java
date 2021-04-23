package fr.exagone.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.exagone.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
