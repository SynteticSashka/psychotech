package ru.psychotech.service;

import ru.psychotech.model.diagnistic.DiagnosticResult;
import ru.psychotech.model.diagnistic.DiagnosticAnswers;
import ru.psychotech.model.diagnistic.DiagnosticDto;

import java.util.List;

public interface DiagnosticService {

  DiagnosticResult getResults(Long clientId, Long diagnosticId);

  DiagnosticDto getDiagnostic(Long id);

  void postResult(Long diagnosticId, Long clientId, DiagnosticAnswers result);

  void clearResults(Long clientId, Long diagnosticId);

  List<DiagnosticDto> getCompleted(Long clientId);

  List<DiagnosticDto> getAvailable(Long clientId);
}
