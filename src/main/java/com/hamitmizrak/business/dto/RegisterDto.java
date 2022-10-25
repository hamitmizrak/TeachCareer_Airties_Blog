package com.hamitmizrak.business.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

//lombok
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Date createdDate;
}
