package desktop.projects.teammanager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {
    private final TaskRepository taskRepository;
    private final TeamRepository teamRepository;
    private final String done;
    private final String inprogress;
    private final String noteam;
    private final String notask;

    public ManagerService(
            TaskRepository taskRepository,
            TeamRepository teamRepository,
            @Value("${task.status.done}") String done,
            @Value("${task.status.progress}") String inprogress,
            @Value("${mesasges.noteam}")String noteam,
            @Value("${mesasges.notask}")String notask) {
        this.taskRepository = taskRepository;
        this.teamRepository = teamRepository;
        this.done = done;
        this.inprogress = inprogress;
        this.noteam = noteam;
        this.notask = notask;
    }

    public Optional<Team> findTeam(String teamName){
        Optional<Team> oTeam = teamRepository.findOneByTeamName(teamName);
        if(oTeam.isEmpty()){
            System.out.println(noteam);
            return Optional.empty();
        }
        return oTeam;
    }

    public Optional<Task> findTask(String taskName){
        Optional<Task> oTask = taskRepository.findOneByTaskName(taskName);
        if(oTask.isEmpty()){
            System.out.println(notask);
            return Optional.empty();
        }
        return oTask;
    }

    public Team createNewTeam(String teamName){
        Team team = new Team(
                teamName,
                new ArrayList<>()
        );
        return teamRepository.save(team);
    }

    public Task createNewTask(Task task){
        taskRepository.save(task);
        Optional<Team> oTeam = findTeam(task.getBelongingTeam());
        if(oTeam.isPresent()){
            Team team = oTeam.get();
            team.getTasks().add(task);
            teamRepository.save(team);
        }
        return task;
    }

    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Optional<Task> modifyTask(String taskName) {
        Optional<Task> oTask = findTask(taskName);
        if(oTask.isPresent()){
            Task task = oTask.get();
            task.setStatus(done);
            taskRepository.save(task);
            return oTask;
        }
        return Optional.empty();
    }

    public void deleteAllTeams(){
        teamRepository.deleteAll();
    }

    public void deleteOneTeam(String teamName){
        Optional<Team> oTeam = findTeam(teamName);
        if(oTeam.isPresent()){
            Team team = oTeam.get();
            teamRepository.delete(team);
        }
    }

    public void deleteOneTask(String taskName){
        Optional<Task> oTask = findTask(taskName);
        if(oTask.isPresent()){
            Task task = oTask.get();
            Optional<Team> oTeam = findTeam(oTask.get().getBelongingTeam());
            if(oTeam.isPresent()){
                Team team = oTeam.get();
                List<Task> tasks = team.getTasks();
                Task specificTask = tasks.stream()
                        .filter(someTask -> someTask.getTaskName().equals(taskName))
                        .findAny()
                        .orElse(null);
                tasks.remove(specificTask);
            }
            taskRepository.delete(task);
        }
    }

    public void deleteAllTasks(){
        taskRepository.deleteAll();
    }
}
