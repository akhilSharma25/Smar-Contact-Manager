package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserForm {
    @NotBlank(message = "username is required")
    @Size(min=3,message = "min 3 character is required")
    private String name;
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "password is required")
    @Size(min=4,message = "min 4 character is required")
    private String password;
    @NotBlank(message = "about is required")
    private String about;
    @Size(min = 8,max = 12,message = "Invalid phone number")
    private String phoneNumber;
}
