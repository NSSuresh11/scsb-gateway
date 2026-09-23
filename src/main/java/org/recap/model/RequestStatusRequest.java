package org.recap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RequestStatusRequest {
    @JsonProperty("barcodes")
    private List<String> Barcodes;
}
