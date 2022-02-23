package ru.psychotech.controller;

import static ru.psychotech.utils.ControllerUtils.getCurrentClientId;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.psychotech.model.diagnistic.DiagnosticResult;
import ru.psychotech.model.diagnistic.DiagnosticDto;
import ru.psychotech.service.DiagnosticService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diagnostic")
public class DiagnosticController {
  private final DiagnosticService service;

  // Получение конкретной диагностики без привязки к пользователю
  @GetMapping("/{id}")
  public ResponseEntity<DiagnosticDto> getDiagnostic(@PathVariable Long id) {
    return ResponseEntity.ok().body(service.getDiagnostic(id));
  }

  //Отправка результатов диагностики
  @PostMapping("/{id}")
  public ResponseEntity<?> postResult(@PathVariable Long id, @RequestBody DiagnosticResult result) {
    service.postResult(id, getCurrentClientId(), result);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // Получение кратких результатов конкретной диагностики

  // Получение подробных результатов конкретной диагностики

  // Получение списка доступных диагностик

  // Получение списка завершённых диагностик

}
