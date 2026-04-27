package com.scm.forms;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserForm {
    private String name;
    private String email;
    private String password;
    private String about;
    private String phoneNumber;
}
