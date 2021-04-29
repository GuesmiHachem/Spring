package de.tekup.ds.repositories;

import de.tekup.ds.models.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<TableEntity,Long> {
}
