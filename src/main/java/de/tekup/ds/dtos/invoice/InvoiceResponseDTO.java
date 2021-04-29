package de.tekup.ds.dtos.invoice;

import de.tekup.ds.dtos.course.CourseResponseDTO;
import de.tekup.ds.dtos.table.TableResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponseDTO {

    private int number;
    private LocalDateTime date;
    private int numberOfTableSets;
    private double total;
    private List<CourseResponseDTO> courses;
    private TableResponseDTO table;
}
