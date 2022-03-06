package ru.psychotech.mapper;

import org.springframework.stereotype.Component;
import ru.psychotech.model.Client;
import ru.psychotech.model.client.ClientDto;
import ru.psychotech.model.client.ClientSummaryDto;
import jooq_generated.tables.pojos.ClientsSummary;
import ru.psychotech.model.client.Gender;

@Component
public class ClientMapper {

  public ClientDto mapClientToDto(Client client) {
    return new ClientDto(
        client.getId(),
        client.getName(),
        client.getLastname(),
        client.getEmail(),
        client.getGender()
    );
  }

  public ClientSummaryDto mapClientSummary(ClientsSummary clientsSummary) {
    return new ClientSummaryDto(
        clientsSummary.getClientId(),
        clientsSummary.getEnergyLevel()
    );
  }
}
