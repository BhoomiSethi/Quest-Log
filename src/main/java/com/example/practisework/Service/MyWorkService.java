package com.example.practisework.Service;

import com.example.practisework.Enum.Status;
import com.example.practisework.Model.ToDoList;
import com.example.practisework.Request.Work;
import com.example.practisework.Request.updatedValues;

import java.util.List;

public interface MyWorkService {
    void addwork(ToDoList work);
    void addManyWork(Work work);
//    void CheckStatus();
    List<ToDoList> incompletetask();
    void updateCompletion(updatedValues value);
    List<ToDoList>completeWork();
    void fullUpdate(String id,ToDoList work);
    void deleteWork(updatedValues es);

}
