package de.tekup.ds.dtos.table;

import de.tekup.ds.dtos.course.CourseResponseDTO;
import de.tekup.ds.dtos.invoice.InvoiceResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class TableResponseDTO {
    private int number;
    private int numberOfTableSets;
    private String type;
    private double additionalFees;
}
