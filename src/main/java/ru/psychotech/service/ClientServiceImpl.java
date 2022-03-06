package ru.psychotech.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.psychotech.mapper.ClientMapper;
import ru.psychotech.model.client.ClientDto;
import ru.psychotech.model.client.ClientSummaryDto;
import ru.psychotech.model.client.EditClient;
import ru.psychotech.model.client.NewClient;
import ru.psychotech.repository.ClientRepository;
import ru.psychotech.repository.ClientSummaryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{
  private final ClientRepository clientRepository;
  private final ClientSummaryRepository summaryRepository;
  private final ClientMapper mapper;

  @Override
  public List<ClientDto> getClients() {
    return clientRepository.getList()
        .stream()
        .map(mapper::mapClientToDto)
        .collect(Collectors.toList());
  }

  @Override
  public ClientDto getClient(Long id) {
    return clientRepository.get(id)
        .map(mapper::mapClientToDto)
        .orElse(null);
  }

  @Override
  public ClientSummaryDto getClientSummary(Long id) {
    return summaryRepository.getClientSummary(id)
        .map(mapper::mapClientSummary)
        .orElse(null);
  }

  @Override
  public ClientDto create(NewClient client) {
    return clientRepository.create(client)
        .map(mapper::mapClientToDto)
        .orElse(null);
  }

  @Override
  public ClientDto update(Long id, EditClient client) {
    return clientRepository.update(id, client)
        .map(mapper::mapClientToDto)
        .orElse(null);
  }

  @Override
  public void delete(Long id) {
    clientRepository.delete(id);
  }
}
