
package CPUscheduling.model;
import java.util.List;

public interface Scheduler {
    void execute(List<Task> taskList) throws InterruptedException;
}
