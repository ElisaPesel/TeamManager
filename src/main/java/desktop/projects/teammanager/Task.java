package desktop.projects.teammanager;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String taskName;

    private String info;
    private String status;
    private String belongingTeam;

    Task(){}

    public Task(String taskName, String info, String status, String belongingTeam) {
        this.taskName = taskName;
        this.info = info;
        this.status = status;
        this.belongingTeam = belongingTeam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBelongingTeam() {
        return belongingTeam;
    }

    public void setBelongingTeam(String belongingTeam) {
        this.belongingTeam = belongingTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(taskName, task.taskName) &&
                Objects.equals(info, task.info) &&
                Objects.equals(status, task.status) &&
                Objects.equals(belongingTeam, task.belongingTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskName, info, status, belongingTeam);
    }
}
