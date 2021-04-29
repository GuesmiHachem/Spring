package de.tekup.ds.services.invoice;

import de.tekup.ds.models.InvoiceEntity;
import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {
    void getRevenuStatistics();
    float getRevenuByPeriod(LocalDate startDate, LocalDate endDate);
    List<InvoiceEntity> getAllInvoices();
    InvoiceEntity getInvoiceByID(long id);
    InvoiceEntity createNewInvoice(InvoiceEntity newInvoice);
    InvoiceEntity deleteInvoicebyID(long id);
    InvoiceEntity updateInvoice(long id ,InvoiceEntity newInvoice);
}
