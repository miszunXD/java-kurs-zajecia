package safe_port;

public class ConfigFileConnection implements AutoCloseable{

    public ConfigFileConnection() {
        System.out.println("[SYSTEM] Otwarto strumień pliku konfiguracyjnego.");
    }

    @Override
    public void close() throws Exception {
        System.out.println("[SYSTEM] Strumień pliku został automatycznie zamknięty.");
    }
}
