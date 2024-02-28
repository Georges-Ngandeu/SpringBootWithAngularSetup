package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.TodoEntity;

import java.util.UUID;

public interface TodoRepository extends CrudRepository<TodoEntity, UUID> {
   
}
