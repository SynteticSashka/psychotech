package ru.psychotech.service;

import ru.psychotech.model.Client;
import ru.psychotech.model.dto.ClientDto;
import ru.psychotech.model.dto.EditClient;
import ru.psychotech.model.dto.NewClient;

import java.util.List;

public interface ClientService {
  List<ClientDto> getClients();

  ClientDto getClient(Long id);

  ClientDto create(NewClient client);

  ClientDto update(Long id, EditClient client);

  void delete(Long id);
}
