package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("todos")
public class TodosController {

    @GetMapping
    public List<String> getAll() {
        return Arrays.asList(
                "take out garbage",
                "do the dishes",
                "walk the dog"
        );
    }

}
