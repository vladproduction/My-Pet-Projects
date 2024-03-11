package com.vladproduction.todoappspringbooth2.configuration;

import com.vladproduction.todoappspringbooth2.entity.ItemToDo;
import com.vladproduction.todoappspringbooth2.repository.ItemToDoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * Created by vladproduction on 11-Mar-24
 */

@Component
public class ItemToDoDataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(ItemToDoDataLoader.class);

    @Autowired
    private ItemToDoRepository repository;
    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        if(repository.count() == 0){
            ItemToDo item1 = new ItemToDo("english");
            ItemToDo item2 = new ItemToDo("java");
            ItemToDo item3 = new ItemToDo("spring");

            repository.save(item1);
            repository.save(item2);
            repository.save(item3);
        }
        logger.info("Number of items ToDo: {}", repository.count());
    }
}
