package de.tekup.ds.services.invoice;

import de.tekup.ds.models.InvoiceEntity;
import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {

    double getRevenuByPeriod(LocalDate startDate, LocalDate endDate);
    double getRevenuByDay(LocalDate day) ;
    List<InvoiceEntity> getAllInvoices();
    InvoiceEntity getInvoiceByID(long id);
    InvoiceEntity createNewInvoice(InvoiceEntity newInvoice);
    InvoiceEntity deleteInvoicebyID(long id);
    InvoiceEntity updateInvoice(long id ,InvoiceEntity newInvoice);
}
