package de.tekup.ds.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Data
@Valid
@Entity(name="clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50 ,nullable = false)
    private String firstName;

    @Column(length = 50 ,nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(length = 100 )
    private String email;

    @Column(length = 20 )
    private String phoneNumber;

    @OneToMany(mappedBy = "client")
    private List<InvoiceEntity> invoices;

}
