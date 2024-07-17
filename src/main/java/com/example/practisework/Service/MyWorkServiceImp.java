package com.example.practisework.Service;

import com.example.practisework.Enum.Status;
import com.example.practisework.Model.ToDoList;
import com.example.practisework.Repository.ToDoRepository;
import com.example.practisework.Request.Work;
import com.example.practisework.Request.updatedValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MyWorkServiceImp implements MyWorkService {
    @Autowired
    private ToDoRepository ToDoRepository;
    @Override
    public void addwork(ToDoList work) {
        work.setId(UUID.randomUUID().toString());
        if(work.isCompleted()==true){
            work.setStatus(Status.COMPLETED);
        }
        else{
            work.setStatus(Status.PENDING);
        }
        ToDoRepository.save(work);
    }

    @Override
    public void addManyWork(Work work) {
        List<ToDoList> myList = new ArrayList<>();
        work.getWorking().forEach(n -> {
            if(n.isCompleted()==true){
                n.setStatus(Status.COMPLETED);
            }
            else{
                n.setStatus(Status.PENDING);
            }
            n.setId(UUID.randomUUID().toString());
            myList.add(n);
        });

        ToDoRepository.saveAll(myList);
    }

    @Scheduled(fixedRate = 5000l)
    public void CheckStatus() {
        ToDoRepository.findAll().forEach(work->{
            if(work.isCompleted()==true){
                work.setStatus(Status.COMPLETED);
                ToDoRepository.save(work);
            }
        });
    }

    @Override
    public List<ToDoList> incompletetask() {
        return ToDoRepository.findByCompleted(false);
    }

    @Override
    public void updateCompletion(updatedValues value) {
        String id = value.getId();
        Optional<ToDoList> findId = ToDoRepository.findById(id);
        if (findId.isPresent()) {
            ToDoList newValue = findId.get();
            newValue.setCompleted(true);
            ToDoRepository.save(newValue);
        }
    }

    @Override
    public void fullUpdate(String id, ToDoList work) {
        Optional<ToDoList> findWork = ToDoRepository.findById(id);
        if (findWork.isPresent()) {
            ToDoList updateWork = ToDoList.builder()
                    .completed(work.isCompleted())
                    .description(work.getDescription())
                    .title(work.getTitle())
                    .id(id)
                    .build();
            ToDoRepository.save(updateWork);
        }

    }

    @Override
    public void deleteWork(updatedValues es) {
        ToDoList delItem= ToDoRepository.findById(es.getId()).orElseThrow(RuntimeException::new);
            ToDoRepository.deleteById(delItem.getId());

    }

    @Override
    public List<ToDoList> completeWork() {
        return ToDoRepository.findByCompletedIs(true);
    }
}

