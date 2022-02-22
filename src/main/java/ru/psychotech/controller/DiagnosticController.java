package ru.psychotech.controller;

import static ru.psychotech.utils.ControllerUtils.getCurrentClientId;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.psychotech.model.diagnistic.Diagnostic;
import ru.psychotech.service.DiagnosticService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diagnostic")
public class DiagnosticController {
  private final DiagnosticService service;

  // Получение конкретной диагностики без привязки к пользователю
  @GetMapping("/{id}")
  public ResponseEntity<Diagnostic> getDiagnostic(@PathVariable Long id) {
    return ResponseEntity.ok().body(service.getDiagnostic(id));
  }
}
