package de.tekup.ds.services.table;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map.Entry;
import de.tekup.ds.helpers.DataHelper;
import de.tekup.ds.models.InvoiceEntity;
import de.tekup.ds.models.TableEntity;
import de.tekup.ds.repositories.InvoiceRepository;
import de.tekup.ds.repositories.TableRepository;

@Service
public class TableServiceImpl implements TableService {

	@Autowired
	private TableRepository tableRepo;
	@Autowired
	private InvoiceRepository invoiceRepo;

	@Override
	public List<TableEntity> getAllTables() {
		return this.tableRepo.findAll();
	}

	@Override
	public TableEntity getTableByID(long id) {
		return this.tableRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Table Not Found"));
	}

	@Override
	public TableEntity createNewTable(TableEntity newTable) {
		return tableRepo.save(newTable);
	}

	@Override
	public TableEntity deleteTablebyID(long id) {
		TableEntity table = this.getTableByID(id);
		this.tableRepo.deleteById(id);
		return table;
	}

	@Override
	public TableEntity updateTable(long id, TableEntity newTable) {
		TableEntity table = this.getTableByID(id);
		BeanUtils.copyProperties(newTable, table, DataHelper.getNullPropertyNames(newTable));
		return this.tableRepo.save(table);
	}

	@Override
	public List<TableEntity> getMostReservedTables() {
		// ==============================
		Map<TableEntity, Long> listcount;
		Optional<Entry<TableEntity, Long>> maxListcount;
		// ==============================
		listcount = this.invoiceRepo.findAll()//
				.stream()//
				.filter(c -> c.getClient() != null)
				.collect(Collectors.groupingBy(InvoiceEntity::getTable, Collectors.counting()));//
		maxListcount = listcount//
				.entrySet()//
				.stream()//
				.max(Comparator.comparing(Map.Entry::getValue));//
		if (maxListcount.isPresent()) {
			return listcount//
					.entrySet()//
					.stream()//
					.filter(c -> c.getValue() == maxListcount.get().getValue())//
					.collect(Collectors.toList())//
					.stream()//
					.map(c -> c.getKey())//
					.collect(Collectors.toList());//
		}
		return new ArrayList<TableEntity>();
	}

}
