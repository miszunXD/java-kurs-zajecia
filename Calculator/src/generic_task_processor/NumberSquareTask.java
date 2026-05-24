package generic_task_processor;

public class NumberSquareTask implements Task<Integer> {
    @Override
    public void execute(Integer data) {
        int result = data * data;
        System.out.printf("Wynik: %d%n ", result);
    }
}
