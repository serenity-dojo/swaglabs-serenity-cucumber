<<<<<<< HEAD
package swaglabs.model;
=======
package starter.model;
>>>>>>> d964fc93779a8cace3a51905fa5f2dcea9287c1a

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductDetails(@JsonProperty("name") String name,
                             @JsonProperty("price") String price,
                             @JsonProperty("description") String description) {}