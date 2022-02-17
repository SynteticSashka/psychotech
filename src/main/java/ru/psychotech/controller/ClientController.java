package ru.psychotech.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.psychotech.model.Client;
import ru.psychotech.model.ClientDto;
import ru.psychotech.repository.ClientRepository;
import ru.psychotech.service.ClientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {
  private final ClientService clientService;

  @GetMapping("/")
  public ResponseEntity<List<ClientDto>> getClients() {
    return ResponseEntity.ok().body(clientService.getClients());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClientDto> getClient(@PathVariable Long id) {
    var client = clientService.getClient(id);
    if (client != null) {
      return ResponseEntity.ok().body(client);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
