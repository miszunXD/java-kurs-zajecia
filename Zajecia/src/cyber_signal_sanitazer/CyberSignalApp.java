package cyber_signal_sanitazer;

import java.util.function.Function;

public class CyberSignalApp {
    public static void main(String[] args) {

        //Krok 1
        SignalStripper step1 = new SignalStripper();

        String text = "   [ALERT]: OMNI_CORP_IS_WATCHING   ";
        String stripped = step1.apply(text);

        System.out.println("Tekst po strippie: " + stripped);

        // Krok 2
        Function<String, String> step2 = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.strip();
            }
        };

        //Krok 3
        Function<String, String> step3 = (String s) -> {
            return s.strip();
        };

        //Krok 4
        Function<String, String> step4 = s -> s.strip();

        //Krok 5
        Function<String, String> step5 = String::strip;

        System.out.println(step1.apply("   [NEON-NET]: CONNECTED   "));
        System.out.println(step2.apply("   [NEON-NET]: CONNECTED   "));
        System.out.println(step3.apply("   [NEON-NET]: CONNECTED   "));
        System.out.println(step4.apply("   [NEON-NET]: CONNECTED   "));
        System.out.println(step5.apply("   [NEON-NET]: CONNECTED   "));
    }

    /*
    Dla Runtime sposób zapisu tej lambdy nie robi zasadniczej różnicy w kontekście optymalizacji.
    Krótszy zapis step5 nie oznacza mniejszej pracy dla procesora. Jest za to zwięzły, elegancki i dość jasny dla
    programisty czytającego kod.
     */
}
