package com.example.practisework.Repository;

import com.example.practisework.Model.ToDoList;
import com.example.practisework.Request.updatedValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ToDoRepository extends JpaRepository<ToDoList,String> {
    @Query("FROM ToDoList where completed=:completed" )
    List<ToDoList> findByCompleted(boolean completed);
    Optional<ToDoList> findById(String id);
    @Query("FROM ToDoList where completed=:completed")
    List<ToDoList>findByCompletedIs(boolean completed);
}
