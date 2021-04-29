package de.tekup.ds.dtos.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableUpdateDTO {
    @Positive
    private int number;
    @Positive
    private int numberOfTableSets;
    @NotBlank
    private String type;
    @Positive
    private double additionalFees;
}
