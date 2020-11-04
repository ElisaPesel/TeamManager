package desktop.projects.teammanager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskEndpoint {

    private final ManagerService service;
    private final String addtask;
    private final String cleartask;
    private final String modifytask;
    private final String cleartasks;

    public TaskEndpoint(
            ManagerService service,
            @Value("${mesasges.addtask}") String addtask,
            @Value("${mesasges.cleartask}") String cleartask,
            @Value("${mesasges.modifytask}") String modifytask,
            @Value("${mesasges.cleartasks}") String cleartasks) {
        this.service = service;
        this.addtask = addtask;
        this.cleartask = cleartask;
        this.modifytask = modifytask;
        this.cleartasks = cleartasks;
    }

    @PostMapping
    Task postTask(@RequestBody Task task){
        System.out.println(addtask);
        return service.createNewTask(task);
    }

    @GetMapping("/{taskName}")
    Task getOneTask(@PathVariable String taskName){
        return service.findTask(taskName)
                .orElse(null);
    }

    @GetMapping
    List<Task> getAllTasks(){
        return service.getAllTasks();
    }

    @PutMapping("/{taskName}")
    Task modifyTask(@PathVariable String taskName){
        System.out.println(modifytask);
        return service.modifyTask(taskName)
                .orElse(null);
    }

    @DeleteMapping("/{taskName}")
    void deleteOneTask(@PathVariable String taskName){
        service.deleteOneTask(taskName);
        System.out.println(cleartask);
    }

    @DeleteMapping
    void deleteAllTasks(){
        service.deleteAllTasks();
        System.out.println(cleartasks);
    }
}
