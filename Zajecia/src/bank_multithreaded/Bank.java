package bank_multithreaded;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.*;

public class Bank {
    private final ConcurrentHashMap<Long, BankAccount> accounts = new ConcurrentHashMap<>();
    ExecutorService executor = Executors.newFixedThreadPool(5);
    private final BlockingQueue<String> transactionsLog = new LinkedBlockingQueue<>();

    public void addAccount(BankAccount bankAccount) {
        accounts.put(bankAccount.getId(), bankAccount);
    }

    public long totalBalance() {
        return accounts.values().stream()
                .mapToLong(BankAccount::getBalance)
                .sum();

    }

    public void startAuditor(String filePath) {
        Thread auditor = new Thread(() -> {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                while (true) {
                    String entry = transactionsLog.take();
                    writer.write(entry);
                    writer.newLine();
                    writer.flush();
                }
            } catch (IOException | InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Audytor");
        auditor.setDaemon(true);
        auditor.start();
    }

    public CompletableFuture<Void> transferAsync(Long fromId, Long toId, long amount) {
        return CompletableFuture.runAsync(() -> {transfer(fromId, toId, amount);}, executor);
    }

    public void transfer(Long fromId, Long toId, long amount) {
        if (fromId.equals(toId)) {
            return;
        }
        BankAccount from = accounts.get(fromId);
        BankAccount to = accounts.get(toId);
        if (from == null || to == null) {
            return;
        }

        BankAccount first = fromId < toId ? from : to;
        BankAccount second = fromId < toId ? to : from;

        synchronized (first) {
            synchronized (second) {
                try {
                    from.withdraw(amount);
                    to.deposit(amount);
                    transactionsLog.put("Przelew z " + fromId + " do " + toId + ", kwota: " + amount);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isLogEmpty() {
        return transactionsLog.isEmpty();
    }

    public void shutdown() {
        executor.shutdown();
    }
}
