package com.hamitmizrak.business.dto;


import com.hamitmizrak.annotation.UserRegisterUniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

//lombok
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterDto implements Serializable {

    private Long id;
    @NotNull(message = "{blog.username.validation.constraints.NotNull.message}")
    private String name;

    @NotNull(message = "{blog.surname.validation.constraints.NotNull.message}")
    private String surname;

    @NotNull(message = "{blog.email.validation.constraints.NotNull.message}")
    @Email(message = "{blog.unique.email.validation.constraints.NotNull.message}")
    @UserRegisterUniqueEmail
    private String email;

    @NotNull(message = "{blog.password.validation.constraints.NotNull.message}")
    @Size(min=7,max = 12,message = "{blog.password.pattern.validation.constraints.NotNull.message}")
    //@Pattern(regexp = "")
    private String password;

    private Date createdDate;
}
