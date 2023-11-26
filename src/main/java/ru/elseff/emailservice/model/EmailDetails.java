package ru.elseff.emailservice.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailDetails {

    @Email(message = "field recipient should be a email")
    @NotNull(message = "field recipient cannot be null")
    String recipient;

    @NotNull(message = "field message cannot be null")
    String message;

    @NotNull(message = "field subject cannot be null")
    String subject;

    String attachment;
}
