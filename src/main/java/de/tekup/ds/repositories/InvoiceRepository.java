package de.tekup.ds.repositories;

import de.tekup.ds.models.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
	List<InvoiceEntity> findAllByDateBetween(LocalDateTime atStartOfDay, LocalDateTime atTime);
}
