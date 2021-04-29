package de.tekup.ds.controllers;

import de.tekup.ds.dtos.table.TableCreateDTO;
import de.tekup.ds.dtos.table.TableResponseDTO;
import de.tekup.ds.dtos.table.TableResponseWithInvoicesDTO;
import de.tekup.ds.dtos.table.TableUpdateDTO;
import de.tekup.ds.models.TableEntity;
import de.tekup.ds.services.table.TableService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/Table")
public class TableController {
	private TableService service;
	private ModelMapper modelMapper;

	@Autowired
	public TableController(TableService service, ModelMapper modelMapper) {
		this.service = service;
		this.modelMapper = modelMapper;
	}

	@GetMapping()
	public List<TableResponseWithInvoicesDTO> getAllTables() {
		return this.service.getAllTables().stream().map(el -> modelMapper.map(el, TableResponseWithInvoicesDTO.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public TableResponseDTO findByid(@PathVariable("id") int id) {
		return this.convertToDto(service.getTableByID(id));
	}

	@DeleteMapping("/delete/{id}")
	public TableResponseDTO deleteTable(@PathVariable("id") long id) {
		return this.convertToDto(this.service.deleteTablebyID(id));
	}

	@PostMapping("/create")
	public TableResponseDTO createNewTable(@Valid @RequestBody TableCreateDTO newTable) {
		return this.convertToDto(service.createNewTable(this.convertToEntity(newTable)));
	}

	@PostMapping("/update/{id}")
	public TableResponseDTO createNewTable(@PathVariable("id") long id, @Valid @RequestBody TableUpdateDTO newTable) {
		return this.convertToDto(service.updateTable(id, modelMapper.map(newTable, TableEntity.class)));
	}

	// 3 - c) Quelle est la table la plus réservée ?
	@GetMapping("/bestTable")
	public List<TableResponseDTO> getBestSellingCourse() {
		return this.convertToDto(service.getMostReservedTables());
	}

	private TableResponseDTO convertToDto(TableEntity table) {
		TableResponseDTO tableResponse = modelMapper.map(table, TableResponseDTO.class);
		return tableResponse;
	}

	private TableEntity convertToEntity(TableCreateDTO tableDTO) {
		return modelMapper.map(tableDTO, TableEntity.class);
	}

	private List<TableResponseDTO> convertToDto(List<TableEntity> listCourse) {
		return listCourse.stream().map(course -> modelMapper.map(course, TableResponseDTO.class))
				.collect(Collectors.toList());
	}
}
