package com.example.practisework.Controller;

import com.example.practisework.Model.ToDoList;
import com.example.practisework.Request.Work;
import com.example.practisework.Request.updatedValues;
import com.example.practisework.Service.MyWorkServiceImp;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static javax.swing.text.html.HTML.Tag.S;
@RestController
public class MyWork {
    @Autowired
    private MyWorkServiceImp myWorkServiceImp;
    @RequestMapping(method= RequestMethod.POST,value = "/todowork")
    public void addwork(@RequestBody ToDoList work ){
        myWorkServiceImp.addwork(work);

    }
    @RequestMapping(method=RequestMethod.POST,value="/listofwork")
    public void addManyWork(@RequestBody Work work){
        myWorkServiceImp.addManyWork(work);
    }

    @RequestMapping(method=RequestMethod.GET,value="/incompleteTask")
    public List<ToDoList>inComplete(){

        return myWorkServiceImp.incompletetask();
    }
    @RequestMapping(method=RequestMethod.PATCH,value="/updatedvalue")
    public void updateWork(@RequestParam String id,@RequestBody updatedValues es){
        myWorkServiceImp.updateCompletion(es);
    }
    @RequestMapping(method=RequestMethod.GET,value="/completeWork")
    public List<ToDoList>completeWork(){
        return myWorkServiceImp.completeWork();
    }
    @RequestMapping(method=RequestMethod.PUT,value="/fullUpdate/{id}")
    public void fullUpdate(@PathVariable String id,@RequestBody ToDoList work){
        myWorkServiceImp.fullUpdate(id,work);
    }
    @RequestMapping(method=RequestMethod.DELETE,value="/deletework/{id}")
    public ResponseEntity<Void> deleteWork(@PathVariable updatedValues id){
        myWorkServiceImp.deleteWork(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
