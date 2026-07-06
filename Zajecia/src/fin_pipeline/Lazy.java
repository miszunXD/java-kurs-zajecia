package fin_pipeline;


import java.util.function.Supplier;

public class Lazy<T> {
    private final Supplier<T> supplier;
    private T value;
    private boolean isComputed = false;

    public Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public T get() {
        if (!isComputed) {
            value = supplier.get();
            isComputed = true;
        }
        return value;
    }
}
