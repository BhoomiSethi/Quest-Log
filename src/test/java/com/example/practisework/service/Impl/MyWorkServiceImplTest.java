package com.example.practisework.service.Impl;

import com.example.practisework.Controller.MyWork;
import com.example.practisework.Enum.Status;
import com.example.practisework.Model.ToDoList;
import com.example.practisework.Repository.ToDoRepository;
import com.example.practisework.Request.updatedValues;
import com.example.practisework.Service.MyWorkServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MyWorkServiceImplTest {
    @Mock
    private ToDoRepository toDoRepository;
    @InjectMocks
    private MyWorkServiceImp myWorkServiceImp;
    @Captor
    private ArgumentCaptor<ToDoList> myWorkCaptor;

    @Test
    public void test_addWork_pending() {
        ToDoList doList1 = ToDoList.builder()
                .id("2233")
                .title("dancing")
                .description("bollywood Dance")
                .completed(false)
                .build();

        //when
        //then
        myWorkServiceImp.addwork(doList1);
        //verify
        verify(toDoRepository).save(myWorkCaptor.capture());
        assertEquals(myWorkCaptor.getValue().getStatus(), Status.PENDING);
    }

    @Test
    public void test_addWork_complete() {
        ToDoList toDoList1 = ToDoList.builder()
                .completed(true)
                .title("drawing")
                .description("cartoon drawing")
                .build();
        //then
        myWorkServiceImp.addwork(toDoList1);
        //verify
        verify(toDoRepository).save(myWorkCaptor.capture());
        assertEquals(myWorkCaptor.getValue().getStatus(), Status.COMPLETED);
    }

    @Test

    public void test_incomplete_task() {
        ToDoList doList1 = ToDoList.builder()
                .id("2233")
                .title("dancing")
                .description("bollywood Dance")
                .completed(false)
                .status(Status.COMPLETED)
                .build();
        List<ToDoList> Response = new ArrayList<>();
        Response.add(doList1);
        //when
        when(toDoRepository.findByCompleted(false)).thenReturn(Response);
        //then
        List<ToDoList> incompletetask = myWorkServiceImp.incompletetask();
        //verify
        verify(toDoRepository).findByCompleted(false);
        assertEquals(incompletetask.get(0), Response.get(0));

    }

    @Test
    public void updateCompletionTest() {
        ToDoList doList1 = ToDoList.builder()

                .title("dancing")
                .description("bollywood Dance")
                .build();
        updatedValues value = updatedValues.builder()
                .id("2233")
                .build();
        //when
        when(toDoRepository.findById(any())).thenReturn(Optional.of(doList1));
        //then
        myWorkServiceImp.updateCompletion(value);
        //verify
        verify(toDoRepository).save((myWorkCaptor.capture()));
        verify(toDoRepository).findById(any());
        assertEquals(myWorkCaptor.getValue().isCompleted(), true);
    }

    @Test
    public void test_full_update(){
        //Given
       ToDoList doListInitial=ToDoList.builder()
               .title("dancing")
               .completed(false)
               .description("bolloywood dance")
               .build();
       ToDoList doListFinal= ToDoList.builder()
               .title("playing")
               .description("cricket")
               .completed(true)
               .build();
       //when
        when(toDoRepository.findById(any())).thenReturn(Optional.of(doListInitial));
        //then
        myWorkServiceImp.fullUpdate("2233",doListFinal);
        //verify

        verify(toDoRepository).findById(any());
        verify(toDoRepository).save(myWorkCaptor.capture());
        assertEquals(myWorkCaptor.getValue().getTitle(),doListFinal.getTitle());
    }

@Test
    public void test_delete_work(){
        //given
    updatedValues id= updatedValues.builder()
            .id("2233")
            .build();
    ToDoList doListInitial=ToDoList.builder()
            .id(id.getId())
            .title("dancing")
            .completed(false)
            .description("bolloywood dance")
            .build();
        //when
    when(toDoRepository.findById(any())).thenReturn(Optional.of(doListInitial));
    doNothing().when(toDoRepository).deleteById(any());
    //then
    myWorkServiceImp.deleteWork(id);
    //verify
    verify(toDoRepository).findById("2233");
    verify(toDoRepository).deleteById("2233");




}

}




