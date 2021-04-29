package de.tekup.ds.controllers;

import de.tekup.ds.dtos.invoice.InvoiceCreateDTO;
import de.tekup.ds.dtos.invoice.InvoiceResponseDTO;
import de.tekup.ds.dtos.invoice.InvoiceUpdateDTO;
import de.tekup.ds.models.InvoiceEntity;
import de.tekup.ds.services.invoice.InvoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/Invoice")
public class InvoiceController {

	private InvoiceService service;
	private ModelMapper modelMapper;

	@Autowired
	public InvoiceController(InvoiceService service, ModelMapper modelMapper) {
		this.service = service;
		this.modelMapper = modelMapper;
	}

	@GetMapping()
	public List<InvoiceResponseDTO> getAllInvoices() {
		return this.service.getAllInvoices().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public InvoiceResponseDTO findByid(@PathVariable("id") int id) {
		return this.convertToDto(service.getInvoiceByID(id));
	}

	@DeleteMapping("/delete/{id}")
	public InvoiceResponseDTO deleteInvoice(@PathVariable("id") long id) {
		return this.convertToDto(this.service.deleteInvoicebyID(id));
	}

	@PostMapping("/create")
	public InvoiceResponseDTO createNewInvoice(@RequestBody InvoiceCreateDTO newInvoice) {
		return this.convertToDto(
				service.createNewInvoice(new InvoiceEntity(newInvoice.getNumber(), newInvoice.getNumberOfTableSets())));
	}

	@PostMapping("/update/{id}")
	public InvoiceResponseDTO createNewInvoice(@PathVariable("id") long id,
			@Valid @RequestBody InvoiceUpdateDTO newInvoice) {
		return this.convertToDto(service.updateInvoice(id,
				new InvoiceEntity(newInvoice.getNumber(), newInvoice.getNumberOfTableSets())));
	}

	// 3 - f) Retourner le revenu pour une période donnée.
	@GetMapping("/getRevenuByPeriod")
	public double getRevenuByPeriod(//
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut, //
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {//
		return service.getRevenuByPeriod(dateDebut, dateFin);
	}

	// 3 - e) Retourner le revenu par jour
	@GetMapping("/getRevenuByDay")
	public double getRevenuByDay(//
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {//
		return service.getRevenuByDay(date);
	}

	// 3 - e) Retourner le revenu par mois
	@GetMapping("/getRevenuByMonth")
	public double getRevenuByMonth(//
			int year, int month) {//
		LocalDate firstDay = LocalDate.of(year, month, Month.of(month).minLength());
		LocalDate lastDay = LocalDate.of(year, month, Month.of(month).maxLength());
		return service.getRevenuByPeriod(firstDay, lastDay);
	}

	// 3 - e) Retourner le revenu par semaine
	@GetMapping("/getRevenuByWeek")
	public double getRevenuByWeek(int numWeek) {//
		LocalDate week = LocalDate.now().with(ChronoField.ALIGNED_WEEK_OF_YEAR, numWeek);
		LocalDate start = week.with(DayOfWeek.MONDAY);
		LocalDate end = start.plusDays(6);
		// System.out.println(start +" - "+ end);
		return service.getRevenuByPeriod(start, end);
	}

	private InvoiceResponseDTO convertToDto(InvoiceEntity invoice) {
		InvoiceResponseDTO invoiceResponse = modelMapper.map(invoice, InvoiceResponseDTO.class);
		return invoiceResponse;
	}

	private InvoiceEntity convertToEntity(InvoiceCreateDTO invoiceDTO) {
		return modelMapper.map(invoiceDTO, InvoiceEntity.class);
	}

}
