package de.tekup.ds.dtos.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableCreateDTO {
    @NotNull
    @Positive
    private int number;
    @NotNull
    @Positive
    private int numberOfTableSets;
    @NotNull
    @NotBlank
    private String type;
    @NotNull
    @Positive
    private double additionalFees;
}
