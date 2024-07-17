package com.example.practisework.Model;

import com.example.practisework.Enum.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Time;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_ToDoList")
public class ToDoList {
    @Id
    @NotBlank
    private String id;
    @NotBlank
    private String title;
    private String description;
    @CreationTimestamp
    private Time timeStart;
    private boolean completed=false;
    @Enumerated(EnumType.STRING)
    private Status status;
//    @PreUpdate
//    @PostUpdate
//    public void setStatus(){
//        if(completed=false){
//            status=Status.PENDING;
//        }
//        else{
//            status=Status.COMPLETED;
//
//        }
//    }


}
