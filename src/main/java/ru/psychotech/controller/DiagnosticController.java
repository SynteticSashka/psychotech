package ru.psychotech.controller;

import static ru.psychotech.utils.ControllerUtils.getCurrentClientId;

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
import ru.psychotech.model.diagnistic.DiagnosticAnswers;
import ru.psychotech.model.diagnistic.DiagnosticDto;
import ru.psychotech.model.diagnistic.DiagnosticResult;
import ru.psychotech.service.DiagnosticService;

import java.util.List;

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
  public ResponseEntity<?> postResult(@PathVariable Long id, @RequestBody DiagnosticAnswers result) {
    service.postResult(id, getCurrentClientId(), result);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // Получение результатов конкретной диагностики
  @GetMapping("/completed/{id}")
  public ResponseEntity<DiagnosticResult> getResult(@PathVariable Long id) {
    return ResponseEntity.ok().body(service.getResults(getCurrentClientId(), id));
  }

  // Получение списка завершённых диагностик
  @GetMapping("/completed/")
  public ResponseEntity<List<DiagnosticDto>> getCompletedDiagnostics() {
    return ResponseEntity.ok().body(service.getCompleted(getCurrentClientId()));
  }

  // Получение списка доступных диагностик
  @GetMapping("/available/")
  public ResponseEntity<List<DiagnosticDto>> getAvailableDiagnostics() {
    return ResponseEntity.ok().body(service.getAvailable(getCurrentClientId()));
  }

  // Очистка результатов
  @DeleteMapping("/clear/{id}")
  public ResponseEntity<?> clearResults(@PathVariable Long id) {
    service.clearResults(getCurrentClientId(), id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // Получение результатов диагностики другим пользователем
}
