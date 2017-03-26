# TODO LIST APP
## 02_TESTING_INTERFACES_WITH_FAKES

This branch follows the app built in branch 1 `01_MINIMAL_CONFIG_SPRING_BOOT_WEB_APP`

The app currently returns data that's hard-coded in the controller.

Let's make a repository, and have that return data to the controller instead.

Let's tart by making an interface.

`src/main/java/com/example/TodoRepo`

```
package com.example;

import java.util.List;

public interface TodoRepo {
    List<String> getAll();
}
```

Let's make an implementation and the corresponding test. We'll call it `HardcodedTodoRepo` as we'll hard code the response in the class. (In the future when we introduce a database, we could make a `JdbcTodoRepo` which returns data from the database.)

`src/test/java/com/example/HardcodedTodoRepoTest`
```
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
```

`src/main/java/com/example/HardcodedTodoRepo`
```
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
```

Now That we have a functioning TodoRepo implementation, we can autowire it into the controller.

To write the controller test, we'll need to make a fake implementation of the `TodoRepo` to use in tests.

`src/test/java/com/example/FakeTodoRepo`
```
public class FakeTodoRepo implements TodoRepo {

    public List<String> getAll_returnValue;

    @Override
    public List<String> getAll() {
        return getAll_returnValue;
    }
}
```

Note the public field `getAll_returnValue`. This allows us to set the return value for the method in tests. Optionally, you can make this field private and define a separate public `setGetAll_returnValue` method for setting the value to avoid accidentally changing the value in tests.

With the fake repo created, let's update the controller test and implementation.

`src/test/java/com/example/TodosControllerTest`
```
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
```

`src/main/java/com/example/TodosController`
```
@RestController
@RequestMapping("todos")
public class TodosController {
    private TodoRepo todoRepo;

    public TodosController(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    @GetMapping
    public List<String> getAll() {
        return todoRepo.getAll();
    }

}
```

Now if we start the app up again `./gradlew bootrun` we'll see the same data as before.
However, we've managed to abstract out the data fetching behavior out to a repo!


Finally, let's think about additional ways to use the `FakeTodoRepo` in the future.
If we were to write a `getOne` method to the interface, how would we implement the fake?
because the method will likely take a parameter, we'd want to test for it as well. We could do that like this.

`src/test/java/com/example/FakeTodoRepo``

```
public String getOne_returnValue;

@Override
public String getOne(int todoId) {
    return getOne_returnValue;
}

```
```
