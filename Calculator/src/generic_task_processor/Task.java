package generic_task_processor;

public interface Task <T> {
    void execute(T data);
}
