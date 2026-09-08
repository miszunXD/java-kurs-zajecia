package bank_multithreaded;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class Main {

    private static final int ACCOUNT_COUNT = 1000;
    private static final int TRANSACTION_COUNT = 1000;
    private static final long STARTING_BALANCE = 1000 * 100L;

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        bank.startAuditor("transactions.log");

        for (long id = 0; id < ACCOUNT_COUNT; id++) {
            bank.addAccount(new BankAccount(id, STARTING_BALANCE));
        }

        long totalStartBalance = bank.totalBalance();
        System.out.println("Total balance: " + totalStartBalance);

        Random random = new Random();
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i < TRANSACTION_COUNT; i++) {
            long fromId = random.nextInt(ACCOUNT_COUNT) ;
            long toId = random.nextInt(ACCOUNT_COUNT);
            long amount = (100 + random.nextInt(401)) * 100L;

            futures.add(bank.transferAsync(fromId, toId, amount));
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        long totalAfterBalance = bank.totalBalance();
        System.out.println("Total balance: " + totalAfterBalance);

        bank.shutdown();

        while (!bank.isLogEmpty()) {
            Thread.sleep(50);
        }
        System.out.println("Log zapisany");
    }
}
