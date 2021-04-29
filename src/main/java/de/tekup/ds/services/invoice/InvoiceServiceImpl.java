package de.tekup.ds.services.invoice;

import de.tekup.ds.helpers.DataHelper;
import de.tekup.ds.models.InvoiceEntity;
import de.tekup.ds.repositories.InvoiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	private InvoiceRepository invoiceRepo;

	@Autowired
	public InvoiceServiceImpl(InvoiceRepository invoiceRepo) {
		this.invoiceRepo = invoiceRepo;
	}

	@Override
	public List<InvoiceEntity> getAllInvoices() {
		return this.invoiceRepo.findAll();
	}

	@Override
	public InvoiceEntity getInvoiceByID(long id) {
		return this.invoiceRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Invoice Not Found"));
	}

	@Override
	public InvoiceEntity createNewInvoice(InvoiceEntity newInvoice) {
		return invoiceRepo.save(newInvoice);
	}

	@Override
	public InvoiceEntity deleteInvoicebyID(long id) {
		InvoiceEntity invoice = this.getInvoiceByID(id);
		this.invoiceRepo.deleteById(id);
		return invoice;
	}

	@Override
	public InvoiceEntity updateInvoice(long id, InvoiceEntity newInvoice) {
		InvoiceEntity invoice = this.getInvoiceByID(id);
		BeanUtils.copyProperties(newInvoice, invoice, DataHelper.getNullPropertyNames(newInvoice));
		return this.invoiceRepo.save(invoice);
	}


	@Override
	public double getRevenuByPeriod(LocalDate startDate, LocalDate endDate) {
		// ==============================
		List<InvoiceEntity> listInvoiceEntity;
		// ==============================
		listInvoiceEntity = this.invoiceRepo//
				.findAllByDateBetween(startDate.atStartOfDay(), endDate.atTime(23, 59, 59));//
		return listInvoiceEntity//
				.stream()//
				.mapToDouble(c -> c.getTotal()).sum();
	}

	@Override
	public double getRevenuByDay(LocalDate day) {
		// ==============================
		List<InvoiceEntity> listInvoiceEntity;
		// ==============================
		listInvoiceEntity = this.invoiceRepo//
				.findAllByDateBetween(day.atStartOfDay(), day.atTime(23, 59, 59));//
		return listInvoiceEntity//
				.stream()//
				.mapToDouble(c -> c.getTotal()).sum();
	}

}
