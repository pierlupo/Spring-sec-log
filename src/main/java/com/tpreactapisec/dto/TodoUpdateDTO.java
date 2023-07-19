package com.tpreactapisec.dto;



import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoUpdateDTO {

    private Integer id;

    @NotEmpty(message = "The title should not be null or empty")
    private String title;

    @NotEmpty(message = "The description name should not be null or empty")
    private String description;

    @NotEmpty(message = "The email should not be null or empty")
    private boolean complete;

}
