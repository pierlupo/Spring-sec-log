package com.tpreactapisec.service.impl;

import com.tpreactapisec.dto.TodoCreateDTO;
import com.tpreactapisec.dto.TodoUpdateDTO;
import com.tpreactapisec.dto.TodoViewDTO;
import com.tpreactapisec.entity.Todo;
import com.tpreactapisec.exception.GlobalException;
import com.tpreactapisec.exception.NotFoundException;
import com.tpreactapisec.repository.TodoRepo;
import com.tpreactapisec.service.TodoService;
import com.tpreactapisec.util.DtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ITodoService implements TodoService {


    @Autowired
    private TodoRepo todoRepo;

    @Autowired
    private DtoUtil dtoUtil;


    @Override
    public TodoViewDTO createTodo(TodoCreateDTO todo) {
        Todo todo1 = dtoUtil.convertToDto(todo, new Todo(), Todo.class);

        Todo todo2 = todoRepo.save(todo1);

        return dtoUtil.convertToDto(todo2, new TodoViewDTO(), TodoViewDTO.class);
    }

    @Override
    public TodoViewDTO updateTodo(Integer id, TodoUpdateDTO todoUpdateDTO) {
        if(todoRepo.findById(id).isPresent()) {
            Todo todo = todoRepo.findById(id).get();
            todo.setTitle(todoUpdateDTO.getTitle());
            todo.setDescription(todoUpdateDTO.getDescription());
            todo.setComplete(todoUpdateDTO.isComplete());
            Todo updateTodo = todoRepo.save(todo);
            return dtoUtil.convertToDto(updateTodo, new TodoViewDTO(), TodoViewDTO.class);
        }else{
            return null;
        }
    }

    @Override
    public TodoViewDTO updateComplete(Integer id, TodoUpdateDTO todoUpdateDTO) throws Exception {
        Todo todo = getTodoByIdFromDatabase(id);
        if(todo != null) {
            todo.setComplete(!todo.isComplete());
            Todo updateComplete = todoRepo.save(todo);
            return dtoUtil.convertToDto(updateComplete, new TodoViewDTO(), TodoViewDTO.class);
        }
        throw new Exception("No todo with this id");
    }

    @Override
    public void deleteTodo(Integer id) {

        Todo todo = getTodoByIdFromDatabase(id);

        todoRepo.deleteById(id);
    }

    @Override
    public List<TodoViewDTO> listAllTodoDto() {

        List<TodoViewDTO> todoList = new ArrayList<>();

        todoRepo.findAll().forEach(employee -> {

            todoList.add(dtoUtil.convertToDto(employee, new TodoViewDTO(), TodoViewDTO.class));
        });

        return todoList;
    }

    @Override
    public TodoViewDTO getTodoById(Integer id) {
        return dtoUtil.convertToDto(todoRepo.findById(id).orElseThrow(() -> new GlobalException("Resource not found")), new TodoViewDTO(), TodoViewDTO.class);

    }

    private Todo getTodoByIdFromDatabase(Integer id) {

        return todoRepo.findById(id).orElseThrow(() -> new NotFoundException("Todo", "id", id));
    }


}
