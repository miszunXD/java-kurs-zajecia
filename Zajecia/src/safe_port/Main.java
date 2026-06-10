package safe_port;

public class Main {
    public static void main(String[] args) {

       try(ConfigFileConnection connection = new ConfigFileConnection()) {
           PortValidation.parseAndValidatePort("null");
       } catch (Exception e) {
           System.out.println("BŁĄD] " + e.getMessage());
       }
    }
}
