package de.tekup.ds.repositories;

import de.tekup.ds.models.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
	List<CourseEntity> findAllByInvoices_dateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
