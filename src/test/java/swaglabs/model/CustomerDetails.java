package swaglabs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CustomerDetails(@JsonProperty("firstName") String firstName,
                              @JsonProperty("lastName") String lastName,
                              @JsonProperty("postCode") String postCode) {

    public static CustomerDetails about(String name) {
        return new CustomerDetails(name, "Mac" + name, "123-ABC");
    }
}
