package ru.psychotech.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.psychotech.model.Client;
import ru.psychotech.repository.ClientRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {
  private final ClientRepository clientRepository;

  @GetMapping("/getAll")
  public ResponseEntity<List<Client>> getAll() {
    return ResponseEntity.ok().body(clientRepository.getList());
  }

}
