package ru.psychotech.controller;

import static ru.psychotech.utils.ControllerUtils.getCurrentClientId;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.psychotech.model.Client;
import ru.psychotech.model.client.ClientDto;
import ru.psychotech.model.client.ClientSummaryDto;
import ru.psychotech.model.client.EditClient;
import ru.psychotech.service.ClientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
  private final ClientService clientService;

  @GetMapping("/summary")
  public ResponseEntity<ClientSummaryDto> getSummary() {
    return ResponseEntity.ok().body(clientService.getClientSummary(getCurrentClientId()));
  }

  @GetMapping("/")
  public ResponseEntity<ClientDto> getCurrentClient() {
    return ResponseEntity.ok().body(clientService.getClient(getCurrentClientId()));
  }

  @PatchMapping("/")
  public ResponseEntity<ClientDto> updateClient(@RequestBody EditClient client) {

    var updated = clientService.update(getCurrentClientId(), client);
    if (updated != null) {
      return ResponseEntity.ok().body(updated);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
