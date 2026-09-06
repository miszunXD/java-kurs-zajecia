package spy_flow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        SecretAgent jamesBond = new SecretAgent(
                "007", 120, true, "James Bond");

        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String json = mapper.writeValueAsString(jamesBond);

        System.out.println(json);

        System.out.println("===Zadanie Level 2===");

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<EnemyBase> bases = mapper.readValue(
                new File("enemy_bases.json"),
                new TypeReference<List<EnemyBase>>() {
                }
        );

        bases.stream()
                .filter(base -> base.threatLevel() > 8)
                .map(EnemyBase::location)
                .forEach(System.out::println);


    }
}
