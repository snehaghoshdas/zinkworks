package com.zinkworks.demo.atm.dto;

import lombok.Data;

@Data
public class IdentityRequestDTO {

    private String cardnumber;
    private String pin;
}
