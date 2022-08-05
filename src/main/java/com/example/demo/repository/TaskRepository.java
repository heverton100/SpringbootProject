package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TaskEntity;

public interface TaskRepository extends JpaRepository <TaskEntity,Long>{

    List<TaskEntity> findByCategory(String category);
    
}
