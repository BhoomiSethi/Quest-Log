package com.example.practisework.Request;

import com.example.practisework.Model.ToDoList;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data

public class Work {
    private List<ToDoList> working;
}
