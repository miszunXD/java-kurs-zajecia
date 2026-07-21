package spy_flow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public record SecretAgent(
        @JsonProperty("agent_alias")
        String codename,

        int missionsCompleted,
        boolean isActive,

        @JsonIgnore
        String realName
) {
}
