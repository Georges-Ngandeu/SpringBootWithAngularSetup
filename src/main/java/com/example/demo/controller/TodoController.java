package com.example.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;

import org.modelmapper.ModelMapper;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.TodoDto;
import com.example.demo.service.TodoService;

import com.example.demo.entity.TodoEntity;
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/todos")
public class TodoController {
    private final TodoService service;
    private final ModelMapper mapper;

    private TodoDto convertToDto(TodoEntity entity) {
        return mapper.map(entity, TodoDto.class);
    }

    private TodoEntity convertToEntity(TodoDto dto) {
        return mapper.map(dto, TodoEntity.class);
    }

    @GetMapping
    public List<TodoDto> getTodos(Pageable pageable) {
        int toSkip = pageable.getPageSize() * pageable.getPageNumber();

        var todoList = StreamSupport
                .stream(service.findAllTodos().spliterator(), false)
                .skip(toSkip).limit(pageable.getPageSize())
                .collect(Collectors.toList());

        return todoList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TodoDto getTodoById(@PathVariable("id") UUID id) {
        return convertToDto(service.findTodoById(id));
    }

    @GetMapping("/count")
    public Map<String, Object> getTodoCount() {
        Map<String, Object> resp = new HashMap<>();
        resp.put("count", service.numberOfTodo());
        return resp;
    }

    @DeleteMapping("/{id}")
    public void deleteTodoById(@PathVariable("id") UUID id) {
        service.removeTodoById(id);
    }

    @PostMapping
    public TodoDto postTodo(@Valid @RequestBody TodoDto TodoDto) {
        var entity = convertToEntity(TodoDto);
        var todo = service.addTodo(entity);

        return convertToDto(todo);
    }

    @PutMapping("/{id}")
    public void putTodo(
            @PathVariable("id") UUID id,
            @Valid @RequestBody TodoDto todoDto
    ) {
        if (!id.equals(todoDto.getId())) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "id does not match"
        );

        var todoEntity = convertToEntity(todoDto);
        service.updateTodo(id, todoEntity);
    }
}
