package com.tpreactapisec.controller;


import com.tpreactapisec.dto.TodoCreateDTO;
import com.tpreactapisec.dto.TodoUpdateDTO;
import com.tpreactapisec.dto.TodoViewDTO;
import com.tpreactapisec.exception.NotFoundException;
import com.tpreactapisec.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService ITodoService;

    @PostMapping(value="",produces = MediaType.APPLICATION_JSON_VALUE)
    public TodoViewDTO createTodo(@Valid @RequestBody TodoCreateDTO todoCreateDTO){

        return ITodoService.createTodo(todoCreateDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoViewDTO> updateTodo(@PathVariable Integer id, @RequestBody TodoUpdateDTO todoUpdateDTO) {

        if(ITodoService.getTodoById(id) == null){
            throw new NotFoundException("Todo", "id", id);
        }

        return new ResponseEntity<>(ITodoService.updateTodo(id, todoUpdateDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<TodoViewDTO> updateComplete(@PathVariable Integer id, @RequestBody TodoUpdateDTO todoUpdateDTO) throws Exception {

        if(ITodoService.getTodoById(id) == null){
            throw new NotFoundException("Todo", "id", id);
        }

        return new ResponseEntity<>(ITodoService.updateComplete(id, todoUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo( @PathVariable Integer id){

        if(ITodoService.getTodoById(id) == null){
            throw new NotFoundException("Todo", "id", id);
        }

        try{
            ITodoService.deleteTodo(id);
            return ResponseEntity.status(204).build();

        }catch (Exception exception){
            throw exception;
        }

    }

    @GetMapping("")
    public ResponseEntity<List<TodoViewDTO>> getAllTodos() {

        return new ResponseEntity(ITodoService.listAllTodoDto(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoViewDTO> getTodoById(@PathVariable(name = "id") Integer id){

        return ResponseEntity.ok(ITodoService.getTodoById(id));

    }
}
