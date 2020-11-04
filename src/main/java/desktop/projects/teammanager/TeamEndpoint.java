package desktop.projects.teammanager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
public class TeamEndpoint {

    private final ManagerService service;
    private final String addteam;
    private final String clearteams;
    private final String clearteam;
    private final String cleartasks;

    public TeamEndpoint(
            ManagerService service,
            @Value("${mesasges.addteam}") String addteam,
            @Value("${mesasges.clearteams}")String clearteams,
            @Value("${mesasges.clearteam}")String clearteam,
            @Value("${mesasges.cleartasks}")String cleartasks) {
        this.service = service;
        this.addteam = addteam;
        this.clearteams = clearteams;
        this.clearteam = clearteam;
        this.cleartasks = cleartasks;
    }

    @PostMapping
    Team postTeam(@RequestBody String teamName){
        System.out.println(addteam);
        return service.createNewTeam(teamName);
    }

    @GetMapping("/{teamName}")
    Team getOneTeam(@PathVariable String teamName){
        return service.findTeam(teamName)
                .orElse(null);
    }

    @GetMapping
    List<Team> getAllTeams(){
        return service.getAllTeams();
    }

    @DeleteMapping
    void deleteAllTeams(){
        service.deleteAllTeams();
        System.out.println(clearteams);
    }

    @DeleteMapping("/{teamName}")
    void deleteOneTeam(@PathVariable String teamName){
        service.deleteOneTeam(teamName);
        System.out.println(clearteam);
    }
}
