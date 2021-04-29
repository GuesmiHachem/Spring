package de.tekup.ds.services.client;

import de.tekup.ds.models.ClientEntity;

import java.util.List;

public interface ClientService {
    List<ClientEntity> getAllClients();
    ClientEntity getClientByID(long id);
    ClientEntity createNewClient(ClientEntity newClient);
    ClientEntity deleteClientbyID(long id);
    ClientEntity updateClient(long id ,ClientEntity newClient);
    List<ClientEntity> getTopClients ();
	List<String> bestDay(int idClient);

}
