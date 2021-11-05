package swaglabs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductDetails(@JsonProperty("name") String name,
                             @JsonProperty("price") String price,
                             @JsonProperty("description") String description) {}
