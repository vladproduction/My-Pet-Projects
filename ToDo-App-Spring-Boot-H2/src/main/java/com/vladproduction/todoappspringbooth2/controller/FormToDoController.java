package com.vladproduction.todoappspringbooth2.controller;

import com.vladproduction.todoappspringbooth2.entity.ItemToDo;
import com.vladproduction.todoappspringbooth2.repository.ItemToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by vladproduction on 11-Mar-24
 */

@Controller
public class FormToDoController {

    @Autowired
    private ItemToDoRepository repository;

    @GetMapping("/create-todo")
    public String showCreateForm(ItemToDo itemToDo){
        return "add-item-todo";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        ItemToDo itemToDo = repository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ItemToDo ID: " + id + " not found"));

        model.addAttribute("todo", itemToDo);
        return "update-item-todo";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") long id, Model model) {
        ItemToDo itemToDo = repository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ItemToDo ID: " + id + " not found"));

        repository.delete(itemToDo);
        return "redirect:/";
    }
}
