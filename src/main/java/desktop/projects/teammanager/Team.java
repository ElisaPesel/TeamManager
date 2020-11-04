package desktop.projects.teammanager;

import javax.persistence.*;
import java.util.*;

@Entity
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String teamName;

    @ElementCollection(fetch = FetchType.EAGER)
    @OneToMany
    private List<Task> tasks = new ArrayList<>();

    Team(){}

    public Team(String teamName, List<Task> tasks) {
        this.teamName = teamName;
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) &&
                Objects.equals(teamName, team.teamName) &&
                Objects.equals(tasks, team.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamName, tasks);
    }
}
