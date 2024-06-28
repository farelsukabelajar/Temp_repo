package com.org.binarfud.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private UUID usersId;
    private String username;
    private String emailAddress;
    private String password;
}
