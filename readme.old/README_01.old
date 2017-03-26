# TODO LIST APP
## 01 MINIMAL_CONFIG_SPRING_BOOT_WEB_APP

Let's make a simple spring boot app.

Go to `http://start.spring.io` and configure the project as below.

- We'll use `gradle` in this example, so select `Gradle Project`
- The group name can be whatever you want, we'll just keep the default of `com.example`
- The `artifact` is the app name. Let's call it `todo-list`
- For dependencies, look for `Web`, select it, and make sure it's included under `Selected Dependencies`.

Once we've configured it, generate it by clicking `Generate Project`.

unzip the downloaded zip file, and open the project.

Let's start by making a controller test, and the corresponding implementation

`TodosControllerTest.java`
```
public class TodosControllerTest {
    @Test
    public void test_getAll_returnsTodos() throws Exception {
        TodosController todosController = new TodosController();
        MockMvc mockController = MockMvcBuilders.standaloneSetup(todosController).build();

        mockController.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]", equalTo("take out garbage")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]", equalTo("do the dishes")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2]", equalTo("walk the dog")));
    }
}
```

`src/main/java/com/example/TodosController.java`
```
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
```

Let's start the app `./gradlew bootRun`
