package desktop.projects.teammanager;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {
    Optional<Task> findOneByTaskName(String taskName);
    List<Task> findAllByBelongingTeam(String belongingTeam);
}
