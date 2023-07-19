package com.tpreactapisec.service;

import com.tpreactapisec.dto.TodoCreateDTO;
import com.tpreactapisec.dto.TodoUpdateDTO;
import com.tpreactapisec.dto.TodoViewDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoService {

    TodoViewDTO createTodo(TodoCreateDTO todoCreateDTO);

    TodoViewDTO updateTodo(Integer id, TodoUpdateDTO todoUpdateDTO);

    TodoViewDTO updateComplete(Integer id, TodoUpdateDTO todoUpdateDTO) throws Exception;

    void deleteTodo(Integer id);

    List<TodoViewDTO> listAllTodoDto();

    TodoViewDTO getTodoById(Integer id);

}
