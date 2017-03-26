package com.example;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class HardcodedTodoRepo implements TodoRepo {
    @Override
    public List<String> getAll() {
        return Arrays.asList(
                "take out garbage",
                "do the dishes",
                "walk the dog"
        );
    }
}
