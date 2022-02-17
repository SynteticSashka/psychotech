package ru.psychotech.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.psychotech.mapper.ClientDtoMapper;
import ru.psychotech.model.Client;
import ru.psychotech.model.ClientDto;
import ru.psychotech.model.NewClient;
import ru.psychotech.repository.ClientRepository;
import ru.psychotech.service.ClientService;

import java.net.URI;
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

  @PostMapping("/")
  public ResponseEntity<ClientDto> createClient(@RequestBody NewClient client) {
    var received = clientService.create(client);
    if (received != null) {
      return ResponseEntity.created(URI.create("/" + received.getId())).body(received);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteClient(@PathVariable Long id) {
    clientService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}