package com.zinkworks.demo.atm.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdentityResponseDTO {
    private String message;
    private String accountNumber;
    private String userId;
}
