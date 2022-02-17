package ru.psychotech.mapper;

import org.springframework.stereotype.Component;
import ru.psychotech.model.Client;
import ru.psychotech.model.ClientDto;

@Component
public class ClientDtoMapper {

  public ClientDto mapClientToDto(Client client) {
    return new ClientDto(
        client.getId(),
        client.getName(),
        client.getLastname(),
        client.getEmail()
    );
  }
}
