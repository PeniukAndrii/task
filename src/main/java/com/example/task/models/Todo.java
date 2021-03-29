package com.example.task.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = "lists")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    private String title;
    private String body;
    private String finalDate;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Lists lists;

    public Todo(String title, String body, String finalDate, Lists lists) {
        this.title = title;
        this.body = body;
        this.finalDate = finalDate;
        this.lists = lists;
    }

}
