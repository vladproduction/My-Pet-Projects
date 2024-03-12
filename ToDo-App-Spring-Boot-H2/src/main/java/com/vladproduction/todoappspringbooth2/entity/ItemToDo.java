package com.vladproduction.todoappspringbooth2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * Created by vladproduction on 11-Mar-24
 */

@Entity
@Table(name = "todo_planner")
@Getter
@Setter
public class ItemToDo implements ItemToDoMBean{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "description required")
    private String description;
    private boolean complete;
    private Instant created;
    private Instant modified;

    public ItemToDo() {
    }

    public ItemToDo(String description) {
        this.description = description;
        this.complete = false;
        this.created = Instant.now();
        this.modified = Instant.now();
    }

    @Override
    public String toString() {
        return String.format("ItemToDo: [" +
                "id = '%d', " +
                "description = '%s', " +
                "complete = '%s', " +
                "created = '%s', " +
                "modified = '%s']", id, description, complete, created, modified);
    }
}
