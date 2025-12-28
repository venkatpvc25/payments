package com.pvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderResponse {
    private String status;
    private String message;

    // Getters and Setters
}
