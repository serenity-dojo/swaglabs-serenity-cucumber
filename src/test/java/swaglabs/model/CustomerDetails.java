package swaglabs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
<<<<<<< HEAD
public record CustomerDetails(@JsonProperty("firstName") String firstName,
                              @JsonProperty("lastName") String lastName,
                              @JsonProperty("postCode") String postCode) {

    public static CustomerDetails about(String name) {
        return new CustomerDetails(name, "Mac" + name, "123-ABC");
    }
}
=======
public record ProductDetails(@JsonProperty("name") String name,
                             @JsonProperty("price") String price,
                             @JsonProperty("description") String description) {}
>>>>>>> d964fc93779a8cace3a51905fa5f2dcea9287c1a
