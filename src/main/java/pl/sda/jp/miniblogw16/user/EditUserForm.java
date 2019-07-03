package pl.sda.jp.miniblogw16.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Getter
@Setter
@ToString
public class EditUserForm {

    @NotNull
    private Long id;

    @NotNull(message = "Pole imię musi być wypełnione.")
    private String firstName;

    @NotBlank(message = "Nazwisko nie może być puste.")
    private String lastName;

    @Pattern(regexp = "[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-z]{2,3}",
            message = "Nieprawidłowy email.")
    private String email;

    @NotEmpty
    private Collection<String> roles;
}
