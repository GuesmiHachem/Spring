package de.tekup.ds.dtos.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceUpdateDTO {

    @Positive
    private int number;
    @Positive
    private int numberOfTableSets;

}
