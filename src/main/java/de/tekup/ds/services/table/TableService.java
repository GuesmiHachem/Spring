package de.tekup.ds.services.table;

import de.tekup.ds.models.TableEntity;

import java.util.List;

public interface TableService {
    List<TableEntity> getMostReservedTables();
    List<TableEntity> getAllTables();
    TableEntity getTableByID(long id);
    TableEntity createNewTable(TableEntity newTable);
    TableEntity deleteTablebyID(long id);
    TableEntity updateTable(long id ,TableEntity newTable);
}
