package generic_task_processor;

public class StringPrinterTask implements Task<String> {
    @Override
    public void execute(String data) {
        System.out.println(data.toUpperCase());
    }
}
