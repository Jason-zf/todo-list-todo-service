package com.thoughtworks.training.todoservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Todo {

    //Id 导入包错误引起缺少identifier错误
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String status;
    private Date dueDate;
    @ManyToMany
    @JoinTable(name = "todo_tag", joinColumns = @JoinColumn(name = "todo_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;
    private Long userId;
}
