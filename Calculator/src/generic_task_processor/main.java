package generic_task_processor;

public class main {
    public static void main(String[] args) {
        Task<String> printTask = new StringPrinterTask();
        TaskRunner<String> stringRunner = new TaskRunner<>(
                printTask, "kurs Java"
        );

        stringRunner.run();

        Task<Integer> squareTask = new NumberSquareTask();
        TaskRunner<Integer> numberRunner = new TaskRunner<>(squareTask, 5);

        numberRunner.run();

    }
}
