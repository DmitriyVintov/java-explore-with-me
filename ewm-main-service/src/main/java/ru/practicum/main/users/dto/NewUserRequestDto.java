package ru.practicum.main.users.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PACKAGE)
public class NewUserRequestDto {
    @NotBlank
    @Size(min = 2, max = 250)
    String name;
    @NotBlank
    @Email
    @Size(min = 6, max = 254)
    String email;
}