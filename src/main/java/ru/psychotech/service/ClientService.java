package ru.psychotech.service;

import ru.psychotech.model.client.ClientDto;
import ru.psychotech.model.client.ClientSummaryDto;
import ru.psychotech.model.client.EditClient;
import ru.psychotech.model.client.NewClient;

import java.util.List;

public interface ClientService {
  List<ClientDto> getClients();

  ClientDto getClient(Long id);

  ClientSummaryDto getClientSummary(Long id);

  ClientDto create(NewClient client);

  ClientDto update(Long id, EditClient client);

  void delete(Long id);
}
