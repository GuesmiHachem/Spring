package de.tekup.ds.controllers;

import de.tekup.ds.dtos.client.ClientCreateDTO;
import de.tekup.ds.dtos.client.ClientResponseDTO;
import de.tekup.ds.dtos.client.ClientUpdateDTO;
import de.tekup.ds.models.ClientEntity;
import de.tekup.ds.services.client.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/Client")
public class ClientController {
	private ClientService service;
	private ModelMapper modelMapper;

	@Autowired
	public ClientController(ClientService service, ModelMapper modelMapper) {
		this.service = service;
		this.modelMapper = modelMapper;
	}

	@GetMapping()
	public List<ClientResponseDTO> getAllClients() {
		return this.service.getAllClients().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ClientResponseDTO findByid(@PathVariable("id") int id) {
		return this.convertToDto(service.getClientByID(id));
	}

	@DeleteMapping("/delete/{id}")
	public ClientResponseDTO deleteClient(@PathVariable("id") long id) {
		return this.convertToDto(this.service.deleteClientbyID(id));
	}

	@PostMapping("/create")
	public ClientResponseDTO createNewClient(@Valid @RequestBody ClientCreateDTO newCLient) {
		return this.convertToDto(service.createNewClient(this.convertToEntity(newCLient)));
	}

	@PostMapping("/update/{id}")
	public ClientResponseDTO createNewClient(@PathVariable("id") long id,
			@Valid @RequestBody ClientUpdateDTO newCLient) {
		return this.convertToDto(service.updateClient(id, modelMapper.map(newCLient, ClientEntity.class)));
	}

	// 3 - b) Quel est le client le plus fidèle au restaurant ?
	@GetMapping("/bestClient")
	public List<ClientResponseDTO> getBestSellingCourse() {
		return this.convertToDto(service.getTopClients());
	}

	// 3 - d) Quel est le jour de la semaine le plus réservé par un client donné ?
	@GetMapping("/bestDay/{idClient}")
	public List<String> getBestDay(@PathVariable int idClient) {
		return service.bestDay(idClient);
	}

	private ClientResponseDTO convertToDto(ClientEntity client) {
		ClientResponseDTO clientResponse = modelMapper.map(client, ClientResponseDTO.class);
		return clientResponse;
	}

	private ClientEntity convertToEntity(ClientCreateDTO clientDTO) {
		return modelMapper.map(clientDTO, ClientEntity.class);
	}

	private List<ClientResponseDTO> convertToDto(List<ClientEntity> listclient) {
		return listclient.stream().map(client -> modelMapper.map(client, ClientResponseDTO.class))
				.collect(Collectors.toList());
	}
}
