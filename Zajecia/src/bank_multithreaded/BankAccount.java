package bank_multithreaded;

import java.util.concurrent.atomic.AtomicLong;

public class BankAccount {
    private final Long id;
    private AtomicLong balance;

    public BankAccount(Long id, long balance) {
        this.id = id;
        this.balance = new AtomicLong(balance);
    }

    public Long getId() {
        return id;
    }

    public long getBalance() {
        return balance.get();
    }

    public void deposit(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Wpłata nie może być mniejsza od 0!");
        }
        balance.updateAndGet(current -> current + amount);
    }

    public void withdraw(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Wypłata środków nie może być mniejsza od 0!");
        }
        balance.updateAndGet(current -> {
            if (current - amount < 0) {
                throw new IllegalArgumentException("Kwota wypłaty nie może być większa od salda rachunku!");
            }
            return current - amount;
        });
    }
}


