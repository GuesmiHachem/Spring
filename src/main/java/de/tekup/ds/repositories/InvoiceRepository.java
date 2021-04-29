package de.tekup.ds.repositories;

import de.tekup.ds.models.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity,Long> {

}
