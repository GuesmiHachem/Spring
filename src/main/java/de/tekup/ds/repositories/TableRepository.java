package de.tekup.ds.repositories;

import de.tekup.ds.models.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<TableEntity,Long> {
}
