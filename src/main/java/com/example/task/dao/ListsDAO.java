package com.example.task.dao;

import com.example.task.models.Lists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ListsDAO extends JpaRepository<Lists, Integer> {

}
