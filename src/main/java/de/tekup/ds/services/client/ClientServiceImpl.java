package de.tekup.ds.services.client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.ds.helpers.DataHelper;
import de.tekup.ds.models.ClientEntity;
import de.tekup.ds.models.InvoiceEntity;
import de.tekup.ds.repositories.ClientRepository;
import de.tekup.ds.repositories.InvoiceRepository;

@Service
public class ClientServiceImpl implements ClientService {
	private ClientRepository clientRepo;

	@Autowired
	private InvoiceRepository invoiceRepo;

	@Autowired
	public ClientServiceImpl(ClientRepository clientRepo) {
		this.clientRepo = clientRepo;
	}

	@Override
	public List<ClientEntity> getAllClients() {
		return this.clientRepo.findAll();
	}

	@Override
	public ClientEntity getClientByID(long id) {
		return this.clientRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Client Not Found"));
	}

	@Override
	public ClientEntity createNewClient(ClientEntity newClient) {
		return clientRepo.save(newClient);
	}

	@Override
	public ClientEntity deleteClientbyID(long id) {
		ClientEntity client = this.getClientByID(id);
		this.clientRepo.deleteById(id);
		return client;
	}

	@Override
	public ClientEntity updateClient(long id, ClientEntity newClient) {
		ClientEntity client = this.getClientByID(id);
		BeanUtils.copyProperties(newClient, client, DataHelper.getNullPropertyNames(newClient));
		return this.clientRepo.save(client);
	}

	@Override
	public List<ClientEntity> getTopClients() {
		// ==============================
		Map<ClientEntity, Long> listCount;
		Optional<Entry<ClientEntity, Long>> maxListCount;
		// ==============================
		listCount = this.invoiceRepo.findAll()//
				.stream()//
				.filter(c -> c.getClient() != null)
				.collect(Collectors.groupingBy(InvoiceEntity::getClient, Collectors.counting()));//

		maxListCount = listCount//
				.entrySet()//
				.stream()//
				.max(Comparator.comparing(Map.Entry::getValue));//
		if (maxListCount.isPresent()) {
			return listCount//
					.entrySet()//
					.stream()//
					.filter(c -> c.getValue() == maxListCount.get().getValue())//
					.collect(Collectors.toList())//
					.stream()//
					.map(c -> c.getKey())//
					.collect(Collectors.toList());//
		}
		return new ArrayList<ClientEntity>();
	}

	@Override
	public List<String> bestDay(int idClient) {
		// ==============================
		List<String> listDay;
		Map<String, Long> listcount;
		Optional<Entry<String, Long>> maxListcount;
		// ==============================
		ClientEntity client = getClientByID(idClient);
		listDay = client.getInvoices()//
				.stream()//
				.map(c -> c.getDate().getDayOfWeek().toString()).collect(Collectors.toList());

		listcount = listDay //
				.stream()//
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));//

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
		return new ArrayList<String>();

	}
}
