package com.vladproduction.todoappspringbooth2.repository;

import com.vladproduction.todoappspringbooth2.entity.ItemToDo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by vladproduction on 11-Mar-24
 */

public interface ItemToDoRepository extends CrudRepository<ItemToDo, Long> {
}
