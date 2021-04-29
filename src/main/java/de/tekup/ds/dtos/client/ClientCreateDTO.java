package de.tekup.ds.dtos.client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientCreateDTO  {
    @NotNull(message = "First name should not be null !")
    @Pattern(regexp = "^[a-zA-Z\\\\s]+",message = "First name field contains illegal characters !")
    @Size(min=2,max=50,message = "First name length must be between 2 and 50 !")
    private String firstName;

    @NotNull(message = "Last name should not be null !")
    @Pattern(regexp = "^[a-zA-Z\\\\s]+",message = "Last name field contains illegal characters !")
    @Size(min=2,max=50,message = "Last name length must be between 2 and 50 !")
    private String lastName;

    @NotNull(message = "Birth Date should not be null !")
    @Past(message = "Invalid birthd date;")
    private LocalDate birthDate;

    @Email(message = "Invalid value of Email")
    private String email;

    @Pattern(regexp = "^[0-9]{8,}$",message = "Bad Phone Number ")
    private String phoneNumber;

}