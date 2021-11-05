package swaglabs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TotalItemPrice(@JsonProperty("itemTotal") String itemTotal,
                             @JsonProperty("tax") String tax,
                             @JsonProperty("total") String total) {}
