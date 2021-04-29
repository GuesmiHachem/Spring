package de.tekup.ds.services.table;


import de.tekup.ds.helpers.DataHelper;
import de.tekup.ds.models.TableEntity;
import de.tekup.ds.repositories.TableRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TableServiceImpl implements TableService {
    private TableRepository tableRepo;

    @Autowired
    public TableServiceImpl(TableRepository tableRepo) {
        this.tableRepo = tableRepo;
    }
    @Override
    public List<TableEntity> getAllTables() {
        return this.tableRepo.findAll();
    }

    @Override
    public TableEntity getTableByID(long id) {
        return this.tableRepo.findById(id).orElseThrow(()->new NoSuchElementException("Table Not Found"));
    }

    @Override
    public TableEntity createNewTable(TableEntity newTable) {
        return tableRepo.save(newTable);
    }

    @Override
    public TableEntity deleteTablebyID(long id)  {
        TableEntity table = this.getTableByID(id);
        this.tableRepo.deleteById(id);
        return table;
    }

    @Override
    public TableEntity updateTable(long id, TableEntity newTable) {
        TableEntity table= this.getTableByID(id);
        BeanUtils.copyProperties(newTable,table, DataHelper.getNullPropertyNames(newTable));
        return this.tableRepo.save(table);
    }


    @Override
    public List<TableEntity> getMostReservedTables(int rank) {
        return null;
    }
}
