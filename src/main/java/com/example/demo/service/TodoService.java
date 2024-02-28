package com.example.demo.service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.example.demo.repository.TodoRepository;
import com.example.demo.entity.TodoEntity;
import com.example.demo.exception.NotFoundException;

import java.util.UUID;

@AllArgsConstructor
@Service
public class TodoService {
    private final TodoRepository repo;

    public Iterable<TodoEntity> findAllTodos() {
        return repo.findAll();
    }

    public TodoEntity findTodoById(UUID id) {
        return findOrThrow(id);
    }

    public void removeTodoById(UUID id) {
        repo.deleteById(id);
    }

    public TodoEntity addTodo(TodoEntity todo) {
        return repo.save(todo);
    }

    public void updateTodo(UUID id, TodoEntity todo) {
        findOrThrow(id);
        repo.save(todo);
    }

    private TodoEntity findOrThrow(final UUID id) {
        return repo
            .findById(id)
            .orElseThrow(
                () -> new NotFoundException("Todo by id " + id + " was not found")
            );
    }

    public Long numberOfTodo(){
        return repo.count();
    } 
}
