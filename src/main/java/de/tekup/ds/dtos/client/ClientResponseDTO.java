package de.tekup.ds.dtos.client;

import de.tekup.ds.dtos.invoice.InvoiceResponseDTO;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ClientResponseDTO {
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String email;
	private String phoneNumber;
	private List<InvoiceResponseDTO> invoices;
}
