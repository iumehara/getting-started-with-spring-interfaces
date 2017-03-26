package com.example;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class HardcodedTodoRepoTest {
    @Test
    public void test_getAll_returnsProjects() throws Exception {
        HardcodedTodoRepo todoRepo = new HardcodedTodoRepo();


        List<String> todos = todoRepo.getAll();


        assertThat(todos.get(0), equalTo("take out garbage"));
        assertThat(todos.get(1), equalTo("do the dishes"));
        assertThat(todos.get(2), equalTo("walk the dog"));
    }
}
