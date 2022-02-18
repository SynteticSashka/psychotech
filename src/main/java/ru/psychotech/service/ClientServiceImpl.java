package ru.psychotech.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.psychotech.mapper.ClientDtoMapper;
import ru.psychotech.model.Client;
import ru.psychotech.model.dto.ClientDto;
import ru.psychotech.model.dto.EditClient;
import ru.psychotech.model.dto.NewClient;
import ru.psychotech.repository.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{
  private final ClientRepository repository;
  private final ClientDtoMapper mapper;

  @Override
  public List<ClientDto> getClients() {
    return repository.getList()
        .stream()
        .map(mapper::mapClientToDto)
        .collect(Collectors.toList());
  }

  @Override
  public ClientDto getClient(Long id) {
    return repository.get(id)
        .map(mapper::mapClientToDto)
        .orElse(null);
  }

  @Override
  public ClientDto create(NewClient client) {
    return repository.create(client)
        .map(mapper::mapClientToDto)
        .orElse(null);
  }

  @Override
  public ClientDto update(Long id, EditClient client) {
    return repository.update(id, client)
        .map(mapper::mapClientToDto)
        .orElse(null);
  }

  @Override
  public void delete(Long id) {
    repository.delete(id);
  }
}
