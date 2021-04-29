package de.tekup.ds.dtos.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceCreateDTO {
    @NotNull
    @Positive
    private int number;
    @Positive
    @NotNull
    private int numberOfTableSets;

}
