package com.example;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.core.IsEqual.equalTo;

public class TodosControllerTest {
    @Test
    public void test_getAll_returnsTodos() throws Exception {
        FakeTodoRepo todoRepo = new FakeTodoRepo();

        todoRepo.getAll_returnValue = Arrays.asList(
                "take out garbage",
                "do the dishes",
                "walk the dog"
        );

        TodosController todosController = new TodosController(todoRepo);
        MockMvc mockController = MockMvcBuilders.standaloneSetup(todosController).build();

        mockController.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]", equalTo("take out garbage")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]", equalTo("do the dishes")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2]", equalTo("walk the dog")));
    }
}
