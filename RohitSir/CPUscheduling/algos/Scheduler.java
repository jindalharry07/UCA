
package CPUscheduling.algos;
import CPUscheduling.model.Task;
import java.util.List;

public interface Scheduler {
    void execute(List<Task> taskList) throws InterruptedException;
}
