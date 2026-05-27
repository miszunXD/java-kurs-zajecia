package generic_task_processor;

public class TaskRunner <T>{
    private final Task<T> task;
    private final T data;

    public TaskRunner(Task<T> task, T data) {

        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null!");
        }

        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null!");
        }

        this.task = task;
        this.data = data;
    }

    public void run() {
        task.execute(data);
    }
}
