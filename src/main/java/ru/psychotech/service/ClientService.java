package ru.psychotech.service;

import ru.psychotech.model.Client;
import ru.psychotech.model.ClientDto;
import ru.psychotech.model.NewClient;

import java.util.List;

public interface ClientService {
  List<ClientDto> getClients();

  ClientDto getClient(Long id);

  ClientDto create(NewClient client);

  ClientDto update(Client client);

  void delete(Long id);
}
