package de.tekup.ds.dtos.table;

import de.tekup.ds.dtos.invoice.InvoiceResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class TableResponseWithInvoicesDTO {
    private int number;
    private int numberOfTableSets;
    private String type;
    private double additionalFees;
    private List<InvoiceResponseDTO> invoices;
}
