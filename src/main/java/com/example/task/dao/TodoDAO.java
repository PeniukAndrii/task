package com.example.task.dao;

import com.example.task.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoDAO extends JpaRepository<Todo,Integer> {
}
