package de.tekup.ds.dtos.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientUpdateDTO {
	@Pattern(regexp = "^[a-zA-Z\\\\s]+", message = "First name field contains illegal characters !")
	@Size(min = 2, max = 50, message = "First name length must be between 2 and 50 !")
	private String firstName;

	@Pattern(regexp = "^[a-zA-Z\\\\s]+", message = "Last name field contains illegal characters !")
	@Size(min = 2, max = 50, message = "Last name length must be between 2 and 50 !")
	private String lastName;

	@Past(message = "Invalid birthd date;")
	private LocalDate birthDate;

	@Email(message = "Invalid value of Email")
	private String email;

	@Pattern(regexp = "^[0-9]{8,}$", message = "Bad Phone Number ")
	private String phoneNumber;

}
