package com.beesket.beesketclone.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginIdCheckDto {
    private String username;
    private String nickname;
}