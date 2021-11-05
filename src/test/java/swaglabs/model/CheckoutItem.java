package swaglabs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CheckoutItem(@JsonProperty("quantity") int quantity,
                           @JsonProperty("description") String description,
                           @JsonProperty("price") String price) {}
