package de.tekup.ds.controllers;

import de.tekup.ds.dtos.invoice.InvoiceCreateDTO;
import de.tekup.ds.dtos.invoice.InvoiceResponseDTO;
import de.tekup.ds.dtos.invoice.InvoiceUpdateDTO;
import de.tekup.ds.helpers.DataHelper;
import de.tekup.ds.models.InvoiceEntity;
import de.tekup.ds.services.invoice.InvoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/Invoice")
public class InvoiceController {

    private InvoiceService service;
    private ModelMapper modelMapper;

    @Autowired
    public InvoiceController(InvoiceService service,ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<InvoiceResponseDTO> getAllInvoices(){
        return this.service.getAllInvoices()
                .stream()
                .map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public InvoiceResponseDTO findByid(@PathVariable("id") int id)
    {
        return this.convertToDto(service.getInvoiceByID(id));
    }
    @DeleteMapping("/delete/{id}")
    public InvoiceResponseDTO deleteInvoice(@PathVariable("id") long id)
    {
        return this.convertToDto(this.service.deleteInvoicebyID(id));
    }

    @PostMapping("/create")
    public InvoiceResponseDTO createNewInvoice( @RequestBody InvoiceCreateDTO newInvoice){
        return this.convertToDto(service.createNewInvoice(
                new InvoiceEntity(newInvoice.getNumber(),newInvoice.getNumberOfTableSets())
        ));
    }

    @PostMapping("/update/{id}")
    public InvoiceResponseDTO createNewInvoice(@PathVariable("id") long id,@Valid @RequestBody InvoiceUpdateDTO newInvoice){
        return this.convertToDto(service.updateInvoice(id,
                new InvoiceEntity(newInvoice.getNumber(),newInvoice.getNumberOfTableSets())
               )
        );
    }


    private InvoiceResponseDTO convertToDto(InvoiceEntity invoice) {
        InvoiceResponseDTO invoiceResponse = modelMapper.map(invoice, InvoiceResponseDTO.class);
        return invoiceResponse;
    }

    private InvoiceEntity convertToEntity(InvoiceCreateDTO invoiceDTO) {
        return modelMapper.map(invoiceDTO, InvoiceEntity.class);
    }

}
