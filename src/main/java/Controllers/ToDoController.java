package Controllers;

import Entities.ToDo;
import Repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDoController {
    @Autowired
    ToDoRepository todoRepository;

    public ToDo addToDo(ToDo todo){
        return todoRepository.save(todo);
    }
}
