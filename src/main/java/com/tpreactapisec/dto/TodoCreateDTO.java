package com.tpreactapisec.dto;

import com.tpreactapisec.entity.TodoList;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoCreateDTO {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "complete")
    private boolean complete;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "todo_list_id")
//    private TodoList todoList;
}
