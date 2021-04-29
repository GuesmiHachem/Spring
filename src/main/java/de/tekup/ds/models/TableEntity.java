package de.tekup.ds.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name="tables")
public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,unique = true)
    private int number;

    @Column(nullable = false)
    private int numberOfTableSets;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private double additionalFees;

    @OneToMany(mappedBy = "table")
    private List<InvoiceEntity> invoices;

}
