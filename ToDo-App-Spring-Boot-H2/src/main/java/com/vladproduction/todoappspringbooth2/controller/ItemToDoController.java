package com.vladproduction.todoappspringbooth2.controller;

import com.vladproduction.todoappspringbooth2.entity.ItemToDo;
import com.vladproduction.todoappspringbooth2.repository.ItemToDoRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.ZoneId;


/**
 * Created by vladproduction on 11-Mar-24
 */

@Controller
public class ItemToDoController {

    private final Logger logger = LoggerFactory.getLogger(ItemToDoController.class);

    @Autowired
    private ItemToDoRepository repository;

    @GetMapping("/")
    public ModelAndView index(){
        logger.info("GET request 'index'");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("itemsToDo", repository.findAll());
        modelAndView.addObject("today", Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
        return modelAndView;
    }

    @PostMapping("/todo")
    public String createItemToDo(@Valid ItemToDo itemToDo, BindingResult result, Model model){
        if (result.hasErrors()){
            return "add-item-todo";
        }
        itemToDo.setCreated(Instant.now());
        itemToDo.setModified(Instant.now());
        repository.save(itemToDo);
        return "redirect:/";
    }

    @PostMapping("/todo/{id}")
    public String updateItemToDo(@PathVariable("id") long id, @Valid ItemToDo itemToDo,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            itemToDo.setId(id);
            return "update-item-todo";
        }

        itemToDo.setModified(Instant.now());
        repository.save(itemToDo);
        return "redirect:/";
    }
}
