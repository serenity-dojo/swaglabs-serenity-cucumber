package swaglabs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
<<<<<<< HEAD
public record TotalItemPrice(@JsonProperty("itemTotal") String itemTotal,
                             @JsonProperty("tax") String tax,
                             @JsonProperty("total") String total) {}
=======
public record CheckoutItem(@JsonProperty("quantity") int quantity,
                           @JsonProperty("description") String description,
                           @JsonProperty("price") String price) {}
>>>>>>> d964fc93779a8cace3a51905fa5f2dcea9287c1a
