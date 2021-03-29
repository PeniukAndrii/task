package com.example.task.models;

import lombok.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = "todo")
public class Lists {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String createdAt;
    private String updatedAt;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "lists_id")
    private List<Todo> todo = new ArrayList<>();


    public Lists(String title, String createdAt, String updatedAt, List<Todo> todo) {
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.todo = todo;
    }

}
